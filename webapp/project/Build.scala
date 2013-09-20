import sbt._
import Keys._

object ApplicationBuild extends Build {

    val appName         = "webapp"
    val appVersion      = "1.0-SNAPSHOT"

    val main = play.Project(appName, appVersion)
}
