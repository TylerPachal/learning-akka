
lazy val commonSettings = Seq(
  organization := "com.akkademy",
  scalaVersion := "2.11.8",
  version := "1.0",
  name := "learning-akka",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.1",
    "com.typesafe.akka" %% "akka-testkit" % "2.5.1" % "test",
    "org.scalatest" % "scalatest_2.11" % "3.0.3"
  )
)

lazy val akkademydb = (project in file("akkademy-db")).settings(commonSettings)

lazy val homeworkch1 = (project in file("homework-ch1")).settings(commonSettings)
