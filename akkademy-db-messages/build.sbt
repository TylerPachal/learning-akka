
organization := "com.akkademy"
scalaVersion := "2.11.8"
version := "0.0.1-SNAPSHOT"
name := "akkademy-db-messages"

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) => Seq("application.conf").contains(name) } }