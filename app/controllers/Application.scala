package controllers

import javax.inject.Inject
import play.Configuration
import play.api.libs.json.Json
import play.api.mvc._

class Application @Inject()(implicit configuration: Configuration) extends Controller {

  def index = Action {
    var availableRoutes = Map.empty[String, String]
    availableRoutes += ("search_stocks_url" -> "GET /stocks?search=${searchTerm}")
    availableRoutes += ("init_stock_watcher_url" -> "GET /watch")
    availableRoutes += ("add_stock_to_watcher_url" -> "POST /stocks Body: { symbol: ${symbol} }")
    availableRoutes += ("remove_stock_from_watcher_url" -> "DELETE /stocks/${symbol}")
    Ok(Json.toJson(availableRoutes))
  }
}