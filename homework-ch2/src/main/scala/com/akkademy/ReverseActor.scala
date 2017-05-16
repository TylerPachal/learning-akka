package com.akkademy

import akka.actor.{Actor, Status}
import com.akkademy.ReverseActor.ReverseRequest

class ReverseActor extends Actor {
  override def receive: Receive = {
    case ReverseRequest(string) => sender() ! string.reverse
    case _ => sender() ! Status.Failure(new ClassNotFoundException)
  }
}

object ReverseActor {
  case class ReverseRequest(string: String)
}