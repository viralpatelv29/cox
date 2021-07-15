
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/viral/cox/backend/cox-coding-exercise/conf/routes
// @DATE:Wed Jul 14 12:43:31 EDT 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
