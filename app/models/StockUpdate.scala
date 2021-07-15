package models

case class StockUpdate(symbol: String, price: BigDecimal)

object StockUpdate {
  import play.api.libs.json._

  implicit val stockUpdateWrites: Writes[StockUpdate] = new Writes[StockUpdate] {
    override def writes(update: StockUpdate): JsValue = Json.obj(
      "symbol" -> update.symbol,
      "price" -> update.price
    )
  }
}
