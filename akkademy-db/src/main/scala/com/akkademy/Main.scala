package com.akkademy

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("akkademy")
  system.actorOf(Props[AkkademyDb], name = "akkademy-db")
}
