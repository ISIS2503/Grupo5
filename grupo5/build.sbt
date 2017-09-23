name := """grupo5"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.mongodb.morphia" % "morphia" % "1.3.2",
  "org.mongodb" % "mongo-java-driver" % "3.2.2"
)
