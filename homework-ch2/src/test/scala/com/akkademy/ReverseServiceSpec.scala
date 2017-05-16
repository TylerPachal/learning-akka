package com.akkademy

import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class ReverseServiceSpec extends FunSpecLike with Matchers {

  private val duration = 5 seconds

  describe("ReverseService") {

    val service = new ReverseService()

    describe("reverse") {

      it ("should return a Future reversed String") {
        val result = Await.result(service.reverse("foobar"), duration)
        result shouldEqual "raboof"
      }

      it ("should work on an empty String") {
        val result = Await.result(service.reverse(""), duration)
        result shouldEqual ""
      }
    }
  }

}
