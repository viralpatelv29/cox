package models

case class StockSearchResult(
  symbol: String,
  name: String,
  exch: String,
  `type`: String,
  exchDisp: String,
  typeDisp: String
) {
  def toMap(): Map[String, String] = {
    Map("symbol" -> this.symbol, "name" -> this.name)
  }
}

object StockSearchResult {
  import play.api.libs.json._

  implicit val reads: Reads[StockSearchResult] = Json.reads[StockSearchResult]
}
