package com.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademy.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

class AkkademyDbSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()

  describe("akkademyDb") {
    describe("given SetRequest") {
      it ("should place key/value into map") {

        // Setup ref and send messages
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("name", "tyler")
        actorRef ! SetRequest("age", 24)

        // Grab a reference to the actual actor, so we can check its map and make sure our keys/values are there
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map("name") shouldEqual "tyler"
        akkademyDb.map("age") shouldEqual 24
      }
    }
  }

}
