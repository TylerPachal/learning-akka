
val akkaV = "2.5.1"

lazy val commonSettings = Seq(
  organization := "com.akkademy",
  scalaVersion := "2.11.8",
  version := "0.0.1-SNAPSHOT",
  name := "learning-akka",

  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-remote" % akkaV,

    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.scalatest" % "scalatest_2.11" % "3.0.3" % "test"
  )
)

lazy val akkademydb = (project in file("akkademy-db")).settings(
  commonSettings,
  name := "akkademy-db",

  mainClass in (Compile,run) := Some("com.akkademy.Main"),

  // Exclude this because the client is using this project and we don't want the client to try to start a remote server
  mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) => Seq("application.conf").contains(name) } }
)

lazy val akkademydbclient = (project in file("akkademy-db-client")).settings(
  commonSettings,
  name := "akkademy-db-client",
  libraryDependencies += "com.akkademy" %% "akkademy-db" % "0.0.1-SNAPSHOT"
)

lazy val homeworkch1 = (project in file("homework-ch1")).settings(
  commonSettings,
  name := "homework-ch1"
)
