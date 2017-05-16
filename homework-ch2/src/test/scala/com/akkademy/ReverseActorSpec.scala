package com.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.ReverseActor.ReverseRequest
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ReverseActorSpec extends FunSpecLike with Matchers {

  private implicit val duration = 5 seconds
  private implicit val timeout = Timeout(duration)
  private implicit val system = ActorSystem()

  describe("ReverseActor") {

    val actorRef = TestActorRef(new ReverseActor)

    describe("given ReverseRequest") {
      it ("should return the string in reverse") {
        val request = ReverseRequest("foobar")
        val result = Await.result(actorRef ? request, duration)
        result shouldEqual "raboof"
      }
    }

    describe("given an unknown request") {
      it ("should return an error") {
        a [ClassNotFoundException] should be thrownBy Await.result(actorRef ? "foobar", duration)
      }
    }

    describe("given a list of strings") {
      it ("should reverse them all") {

        val strings = Seq("tyler", "foobar", "toronto", "fridge")
        val expected = Seq("relyt", "raboof", "otnorot", "egdirf")

        val futureResults = strings.map(s => actorRef ? ReverseRequest(s))
        val results = Await.result(Future.sequence(futureResults), duration)

        results should contain theSameElementsInOrderAs expected
      }
    }
  }
}
