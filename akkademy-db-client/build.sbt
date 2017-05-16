
scalaVersion := "2.11.8"
version := "0.0.1-SNAPSHOT"
name := "akkademy-db-client"

val akkaV = "2.5.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-remote" % akkaV,
  "com.akkademy" %% "akkademy-db-messages" % "0.0.1-SNAPSHOT",

  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "org.scalatest" % "scalatest_2.11" % "3.0.3" % "test"
)