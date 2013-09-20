import sbt._
import Keys._
import PlayProject._

import sbtassembly.Plugin._
import AssemblyKeys._


object TaggerBuild extends Build{
  //settings
  val buildOrganization = "edu.wash"
  val description = "Tag sentences with XML-specified logic."
  val buildVersion = "0.2"
  val buildScalaVersions = Seq("2.10.2")

  val pomExtra = (
    <scm>
      <url>https://github.com/knowitall/taggers</url>
      <connection>scm:git://github.com/knowitall/taggers.git</connection>
      <developerConnection>scm:git:git@github.com:knowitall/taggers.git</developerConnection>
      <tag>HEAD</tag>
    </scm>
    <developers>
     <developer>
        <name>Michael Schmitz</name>
      </developer>
    </developers>)


  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"

  lazy val taggers = Project(id = "taggers", base = file(".")) settings (
    publish := { },
    publishLocal := { }
  ) aggregate(taggercore, webapp)

  // parent build definition
  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := "2.10.2",
    Keys.fork in run := true,
    Keys.fork in Test := true,
    resolvers ++= Seq(
      "knowitall" at "http://knowitall.cs.washington.edu/maven2",
      "knowitall-snapshot" at "http://knowitall.cs.washington.edu/maven2-snapshot",
      mavenLocal)
  ) ++ assemblySettings

  lazy val taggercore = Project(id = "taggercore", base = file("taggercore"), settings = buildSettings ++ Seq(
  libraryDependencies ++= Seq(
    "com.google.guava" % "guava" % "13.0.1",
    "edu.washington.cs.knowitall.nlptools" %% "nlptools-core" % "2.4.2",
    "edu.washington.cs.knowitall.nlptools" %% "nlptools-chunk-opennlp" % "2.4.2",
    "edu.washington.cs.knowitall.nlptools" %% "nlptools-stem-morpha" % "2.4.2",
    "edu.washington.cs.knowitall.nlptools" %% "nlptools-typer-stanford" % "2.4.2",
    "edu.washington.cs.knowitall" % "openregex" % "1.0.3",
    "org.apache.commons" % "commons-lang3" % "3.1",
    "org.jdom" % "jdom2" % "2.0.5",
    "junit" % "junit" % "4.11" % "test",
    "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
    "org.specs2" % "specs2_2.10" % "1.12.3" % "test"),
    resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  ))

  lazy val webapp = play.Project("webapp", "0.0", path = file("webapp"), settings = buildSettings).dependsOn(taggercore)

}
