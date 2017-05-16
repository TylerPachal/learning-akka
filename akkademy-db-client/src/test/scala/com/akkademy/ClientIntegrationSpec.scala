package com.akkademy

import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class ClientIntegrationSpec extends FunSpecLike with Matchers {

  val client = new Client("127.0.0.1:2552")

  describe("akkademyDbClient") {
    it ("should set a new value") {
      client.set("123", false)
      val result = Await.result(client.get("123"), 10 seconds)
      result shouldEqual false
    }
  }
}
