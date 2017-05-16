package com.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.messages.{GetRequest, SetRequest}

import scala.concurrent.duration._

class Client(remoteAddress: String) {

  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem()
  private val remoteDB = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")

  def set(key: String, value: Any) = {
    remoteDB ! SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDB ? GetRequest(key)
  }

}
