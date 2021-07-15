package controllers

import java.net.URI

import javax.inject.{ Inject, Singleton }
import models.StockSearchResult
import play.api.libs.json.{ JsValue, Json }
import play.api.mvc._
import services.StockService

import scala.concurrent.{ ExecutionContext, Future }

@Singleton
class Stocks @Inject()(implicit executionContext: ExecutionContext, stockService: StockService) extends Controller {

  def watch(): WebSocket = {
    WebSocket.acceptOrResult[JsValue, JsValue] {
      case rh if sameOriginCheck(rh) =>
        Future.successful(stockService.stockFlow).map { flow =>
          Right(flow)
        }
    }
  }

  def search(searchTerm: Option[String]) = Action.async {
    val result: Future[Seq[StockSearchResult]] = stockService.search(searchTerm)
    result.map { searchResults =>
      Ok(convertStockSearchToJson(searchResults))
    }
  }

  def average(searchTerm: Option[String]) = Action.async {
    val result: Future[Seq[StockSearchResult]] = stockService.search(searchTerm)
    result.map { searchResults =>
      Ok(convertStockSearchToJson(searchResults))
    }
  }

  private def convertStockSearchToJson(searchResult: Seq[StockSearchResult]): JsValue = {
    Json.toJson( searchResult.map { sr => sr.toMap() } )
  }

  def add = Action(parse.tolerantJson) { request: Request[JsValue] =>
    val stockSymbol: String = (request.body \ "symbol").as[String]
    if (!stockService.alreadyWatchingStock(stockSymbol)) {
      stockService.addStock(stockSymbol)
      Ok("{\"success\":true}")
    } else {
      Ok("{\"success\":true, \"message\": \"Already watching stock\"}")
    }
  }

  def remove(symbol: String) = Action {
    stockService.unwatchStock(symbol)
    Ok("{\"success\":true}")
  }

  private def sameOriginCheck(implicit rh: RequestHeader): Boolean = {

    rh.headers.get("Origin") match {
      case Some(originValue) if originMatches(originValue) =>
        true

      case Some(badOrigin) =>
        print(s"originCheck: rejecting request because Origin header value ${badOrigin} is not in the same origin")
        false

      case None =>
        false
    }
  }

  private def originMatches(origin: String): Boolean = {
    try {
      val url = new URI(origin)
      url.getHost == "localhost" &&
        (url.getPort match { case 9000 | 4200 => true; case _ => false })
    } catch {
      case e: Exception => false
    }
  }
}
