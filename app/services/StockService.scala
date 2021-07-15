package services

import akka.NotUsed
import akka.stream.scaladsl.{ BroadcastHub, Flow, Keep, MergeHub, RunnableGraph, Source }
import akka.stream.{ KillSwitches, Materializer, ThrottleMode, UniqueKillSwitch }
import javax.inject.{ Inject, Singleton }
import models.{ StockSearchResult, StockUpdate }
import play.Configuration
import play.api.libs.json.{ JsValue, Json }
import play.api.libs.ws.{ WSClient, WSRequest }
import yahoofinance.{ Stock, YahooFinance }

import scala.concurrent.{ ExecutionContext, Future }
import scala.concurrent.duration._

@Singleton
class StockService @Inject()(implicit mat: Materializer,
                              ws: WSClient, ec: ExecutionContext,
                              configuration: Configuration)  {

  private var stocksMap: Map[String, UniqueKillSwitch] = Map.empty
  private val (sink, defaultSource) = MergeHub.source[JsValue](perProducerBufferSize = 16)
    .toMat(BroadcastHub.sink(bufferSize = 256))(Keep.both)
    .run()

  val stockFlow: Flow[JsValue, JsValue, _] = {
    Flow.fromSinkAndSource(sink, defaultSource)
  }

  private def setupNewStockSource(symbol: String): Source[JsValue, NotUsed] = {
    Source.repeat(NotUsed)
      .throttle(elements = 1, per = 1000.millis, maximumBurst = 1, ThrottleMode.shaping)
      .map(_ => {
        val stock: Stock = YahooFinance.get(symbol)
        val currentPrice = stock.getQuote.getPrice
        val stockUpdate: StockUpdate = new StockUpdate(symbol, currentPrice)
        Json.toJson(stockUpdate)
      })
  }

  def addStock(symbol: String): Unit = {

    val stockSource = this.setupNewStockSource(symbol)

    val killSwitchFlow: Flow[JsValue, JsValue, UniqueKillSwitch] = {
      Flow.apply[JsValue]
        .joinMat(KillSwitches.singleBidi[JsValue, JsValue])(Keep.right)
    }

    val graph: RunnableGraph[UniqueKillSwitch] = {
      stockSource
        .viaMat(killSwitchFlow)(Keep.right)
        .to(sink)
    }

    val killSwitch: UniqueKillSwitch = graph.run()

    stocksMap += (symbol -> killSwitch)
  }

  def alreadyWatchingStock(symbol: String): Boolean = {
    stocksMap.nonEmpty && stocksMap.exists(x => x._1 == symbol)
  }

  def unwatchStock(symbol: String): Unit = {
    stocksMap.get(symbol).foreach { killSwitch =>
      killSwitch.shutdown()
    }
    stocksMap -= symbol
  }

  def search(searchTerm: Option[String]): Future[Seq[StockSearchResult]] = {
    val searchStocksUrl: String = configuration.getString("application.services.searchStocksUrl")
    val emptyRequest: WSRequest = ws.url(searchStocksUrl)
    var queryParams = Map("region" -> "US", "lang" -> "en-US")
    val searchTermVal: String = searchTerm.getOrElse("")
    if (!searchTermVal.isEmpty) {
      queryParams += "query" -> searchTermVal
    }
    val request: WSRequest =
      emptyRequest
        .withHeaders("Accept" -> "application/json")
        .withQueryString(queryParams.toSeq : _*)
        .withRequestTimeout(10000.millis)

    request.get().map(response => {
      (response.json \ "ResultSet" \ "Result").as[Seq[StockSearchResult]]
    })
  }
}
