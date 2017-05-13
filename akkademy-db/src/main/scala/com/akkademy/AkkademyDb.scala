package com.akkademy

import akka.actor.Actor
import akka.event.Logging
import com.akkademy.messages.SetRequest

import scala.collection.mutable

class AkkademyDb extends Actor {

  val map = new mutable.HashMap[String, Any]
  val log = Logging(context.system, this)

  override def receive: Receive = {

    case SetRequest(key, value) =>
      log.info(s"Received SetRequest - key: $key value: $value")
      map.put(key, value)

    case o => log.info(s"Received an unknown message: $o")
  }
}
