import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "ekinoDevoxxPlayer"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
        //"com.ekino.animation" % "client" % "1.0-SNAPSHOT"
        //"com.google.guava" % "guava" % "14.0",
        //"com.jayway.restassured" % "rest-assured" % "1.7" % "test"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
      //.settings(cloudBeesSettings :_*)
      //.settings(CloudBees.applicationId := Some("ekinoDevoxxPlayer"))
      //.settings(CloudBees.deployParams += ("java_version" -> "1.7"))
    )

    // resolvers += "JBoss repository" at "https://repository.jboss.org/nexus/content/repositories/",
}
