# cox

cox scala code for stock watcher using yahoo finance api

Install SBT which will run on akka http server.

Following are the routes exposed:

# Routes
# This file defines all application routes (Higher priority routes first)

# api controllers mapping
GET     /api/stocks                  controllers.api.StockController.getAll
GET     /api/stocks/:id                  controllers.api.StockController.getById(id: Long)
POST    /api/stocks/add              controllers.api.StockController.add
PUT     /api/stocks/update/:id           controllers.api.StockController.update(id: Long)
DELETE /api/stocks/delete/:id         controllers.api.StockController.delete(id: Long)

