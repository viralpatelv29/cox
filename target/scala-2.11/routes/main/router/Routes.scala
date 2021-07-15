
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/viral/cox/backend/cox-coding-exercise/conf/routes
// @DATE:Wed Jul 14 12:43:31 EDT 2021

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Application_1: controllers.Application,
  // @LINE:9
  Stocks_2: controllers.Stocks,
  // @LINE:25
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Application_1: controllers.Application,
    // @LINE:9
    Stocks_2: controllers.Stocks,
    // @LINE:25
    Assets_0: controllers.Assets
  ) = this(errorHandler, Application_1, Stocks_2, Assets_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_1, Stocks_2, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Application.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """watch""", """controllers.Stocks.watch"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stocks""", """controllers.Stocks.search(search:Option[String])"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stocks""", """controllers.Stocks.average(average:Option[String])"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stocks""", """controllers.Stocks.add"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stocks/""" + "$" + """symbol<[^/]+>""", """controllers.Stocks.remove(symbol:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Application_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    Application_1.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """ Generic index route which returns api structure""",
      this.prefix + """"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Stocks_watch1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("watch")))
  )
  private[this] lazy val controllers_Stocks_watch1_invoker = createInvoker(
    Stocks_2.watch,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Stocks",
      "watch",
      Nil,
      "GET",
      """ watch for stock price changes for stocks that have been added to the stock watcher(websocket)""",
      this.prefix + """watch"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Stocks_search2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stocks")))
  )
  private[this] lazy val controllers_Stocks_search2_invoker = createInvoker(
    Stocks_2.search(fakeValue[Option[String]]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Stocks",
      "search",
      Seq(classOf[Option[String]]),
      "GET",
      """ search for stocks""",
      this.prefix + """stocks"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Stocks_average3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stocks")))
  )
  private[this] lazy val controllers_Stocks_average3_invoker = createInvoker(
    Stocks_2.average(fakeValue[Option[String]]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Stocks",
      "average",
      Seq(classOf[Option[String]]),
      "GET",
      """ search for stocks""",
      this.prefix + """stocks"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_Stocks_add4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stocks")))
  )
  private[this] lazy val controllers_Stocks_add4_invoker = createInvoker(
    Stocks_2.add,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Stocks",
      "add",
      Nil,
      "POST",
      """ Add stock to watcher
 Accepts body of { "symbol": """" + "$" + """{stockSymbol}" }""",
      this.prefix + """stocks"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_Stocks_remove5_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stocks/"), DynamicPart("symbol", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Stocks_remove5_invoker = createInvoker(
    Stocks_2.remove(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Stocks",
      "remove",
      Seq(classOf[String]),
      "DELETE",
      """ Remove stock from watcher""",
      this.prefix + """stocks/""" + "$" + """symbol<[^/]+>"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_Assets_at6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at6_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(Application_1.index)
      }
  
    // @LINE:9
    case controllers_Stocks_watch1_route(params) =>
      call { 
        controllers_Stocks_watch1_invoker.call(Stocks_2.watch)
      }
  
    // @LINE:12
    case controllers_Stocks_search2_route(params) =>
      call(params.fromQuery[Option[String]]("search", None)) { (search) =>
        controllers_Stocks_search2_invoker.call(Stocks_2.search(search))
      }
  
    // @LINE:15
    case controllers_Stocks_average3_route(params) =>
      call(params.fromQuery[Option[String]]("average", None)) { (average) =>
        controllers_Stocks_average3_invoker.call(Stocks_2.average(average))
      }
  
    // @LINE:19
    case controllers_Stocks_add4_route(params) =>
      call { 
        controllers_Stocks_add4_invoker.call(Stocks_2.add)
      }
  
    // @LINE:22
    case controllers_Stocks_remove5_route(params) =>
      call(params.fromPath[String]("symbol", None)) { (symbol) =>
        controllers_Stocks_remove5_invoker.call(Stocks_2.remove(symbol))
      }
  
    // @LINE:25
    case controllers_Assets_at6_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at6_invoker.call(Assets_0.at(path, file))
      }
  }
}
