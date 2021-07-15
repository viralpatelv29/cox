name := "cox-scala-exercise"
 
version := "1.0" 
      
lazy val `cox-scala-exercise` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq( jdbc , cache , ws , specs2 % Test )
libraryDependencies += "com.yahoofinance-api" % "YahooFinanceAPI" % "3.15.0"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      