package com.akkademy

import akka.actor.Actor
import akka.actor.Status.{Failure, Success}
import akka.event.Logging
import com.akkademy.messages._

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
        case None => sender() ! Failure(KeyNotFoundException(key))
        case Some(value) => sender() ! value
      }

    case Delete(key) =>
      log.info(s"Received Delete - key: $key")
      map.get(key) match {
        case None => sender() ! Failure(KeyNotFoundException(key))
        case Some(_) =>
          map.remove(key)
          sender() ! Success
      }

    case SetIfNotExists(key, value) =>
      log.info(s"Received SetIfNotExists - key: $key value: $value")
      map.get(key) match {
        case Some(_) => sender() ! Failure(KeyExistsException(key))
        case None =>
          map.put(key, value)
          sender() ! Success
      }

    case o =>
      log.info(s"Received an unknown message: $o")
      sender() ! Failure(new ClassNotFoundException)
  }
}
