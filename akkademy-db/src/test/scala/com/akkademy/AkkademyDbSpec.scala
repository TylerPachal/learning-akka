package com.akkademy

import akka.actor.ActorSystem
import akka.actor.Status.Success
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akkademy.messages._
import com.typesafe.config.ConfigFactory
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()
  private val duration = 5 seconds
  private implicit val timeout = Timeout(duration)

  describe("akkademyDb") {

    val actorRef = TestActorRef(new AkkademyDb)
    val akkademyDb = actorRef.underlyingActor

    describe("given SetRequest") {
      it ("should place key/value into map") {

        // Send messages
        actorRef ! SetRequest("name", "tyler")
        actorRef ! SetRequest("age", 24)

        // Make sure map contains the entries
        akkademyDb.map("name") shouldEqual "tyler"
        akkademyDb.map("age") shouldEqual 24
      }
    }

    describe("given Delete") {
      it ("should successfully delete if the key exists") {

        actorRef ! SetRequest("city", "toronto")

        // Delete it
        val result = Await.result(actorRef ? Delete("city"), duration)
        result shouldEqual Success
        akkademyDb.map.get("city") shouldEqual None
      }

      it ("should return failure if key didn't exist") {
        a [KeyNotFoundException] should be thrownBy Await.result(actorRef ? Delete("onion"), duration)
        akkademyDb.map.get("onion") shouldEqual None
      }
    }

    describe("given SetIfNotExists") {
      it ("should successfully set a value if it doesn't exist") {
        val result = Await.result(actorRef ? SetIfNotExists("car", "jeep"), duration)
        result shouldEqual Success
        akkademyDb.map.get("car") shouldEqual Some("jeep")
      }

      it ("should return failure if the key already existed") {
        actorRef ! SetIfNotExists("color", "red")
        a [KeyExistsException] should be thrownBy Await.result(actorRef ? SetIfNotExists("color", "blue"), duration)
        akkademyDb.map.get("color") shouldEqual Some("red")
      }
    }
  }
}
