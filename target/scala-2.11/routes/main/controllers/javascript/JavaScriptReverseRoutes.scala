
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/viral/cox/backend/cox-coding-exercise/conf/routes
// @DATE:Wed Jul 14 12:43:31 EDT 2021

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:9
  class ReverseStocks(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def remove: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Stocks.remove",
      """
        function(symbol0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "stocks/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("symbol", encodeURIComponent(symbol0))})
        }
      """
    )
  
    // @LINE:15
    def average: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Stocks.average",
      """
        function(average0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "stocks" + _qS([(""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("average", average0)])})
        }
      """
    )
  
    // @LINE:19
    def add: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Stocks.add",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "stocks"})
        }
      """
    )
  
    // @LINE:12
    def search: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Stocks.search",
      """
        function(search0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "stocks" + _qS([(""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("search", search0)])})
        }
      """
    )
  
    // @LINE:9
    def watch: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Stocks.watch",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "watch"})
        }
      """
    )
  
  }

  // @LINE:25
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
