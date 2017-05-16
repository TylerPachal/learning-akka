package com.akkademy

import akka.actor.Actor
import akka.event.Logging

class LastThingActor extends Actor {

  import LastThingActor._

  val log = Logging(context.system, this)
  var lastMessage = Option.empty[String]

  override def receive: Receive = {
    case StoreRequest(message) =>
      log.info(s"Received Store: $message")
      lastMessage = Some(message)
    case o => log.info(s"Received an unknown message: $o")
  }

}

object LastThingActor {
  case class StoreRequest(message: String)
}
