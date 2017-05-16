package com.akkademy

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.ReverseActor.ReverseRequest

import scala.concurrent.Future
import scala.concurrent.duration._

class ReverseService {

  protected implicit val timeout = Timeout(5 seconds)

  private implicit val system = ActorSystem()
  private val actor = system.actorOf(Props[ReverseActor], "reverser")

  def reverse(string: String): Future[String] = {
    val futureResponse = actor ? ReverseRequest(string)
    futureResponse.mapTo[String]
  }
}
