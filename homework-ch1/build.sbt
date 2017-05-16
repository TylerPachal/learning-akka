
organization := "com.akkademy"
scalaVersion := "2.11.8"
version := "0.0.1-SNAPSHOT"
name := "homework-ch1"

val akkaV = "2.5.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,

  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "org.scalatest" % "scalatest_2.11" % "3.0.3" % "test"
)
