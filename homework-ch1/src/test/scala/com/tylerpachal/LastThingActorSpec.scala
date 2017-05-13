package com.tylerpachal

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.tylerpachal.LastThingActor.StoreRequest
import org.scalatest.{FunSpecLike, Matchers}

class LastThingActorSpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()

  describe("LastThingActor") {
    describe("given StoreRequest") {

      it ("should update the lastMessage value") {
        val actorRef = TestActorRef(new LastThingActor)
        actorRef ! StoreRequest("foobar")
        actorRef.underlyingActor.lastMessage shouldEqual Some("foobar")
      }

      it ("should retain the most recent message if multiple are sent") {
        val actorRef = TestActorRef(new LastThingActor)
        actorRef ! StoreRequest("foo")
        actorRef ! StoreRequest("bar")
        actorRef ! StoreRequest("Toronto Blue Jays")
        actorRef.underlyingActor.lastMessage shouldEqual Some("Toronto Blue Jays")
      }
    }
  }
}
