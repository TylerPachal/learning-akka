package com.akkademy

import akka.actor.{Actor, Status}
import akka.actor.Status.Success
import akka.event.Logging
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable

class AkkademyDb extends Actor {

  val map = new mutable.HashMap[String, Any]
  val log = Logging(context.system, this)

  override def receive: Receive = {

    case SetRequest(key, value) =>
      log.info(s"Received SetRequest - key: $key value: $value")
      map.put(key, value)
      sender() ! Success

    case GetRequest(key) =>
      log.info(s"Received GetRequest - key: $key")
      map.get(key) match {
        case None => sender() ! Status.Failure(KeyNotFoundException(key))
        case Some(value) => sender() ! value
      }

    case o =>
      log.info(s"Received an unknown message: $o")
      sender() ! Status.Failure(new ClassNotFoundException)
  }
}
