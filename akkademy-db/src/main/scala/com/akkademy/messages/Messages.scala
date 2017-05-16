package com.akkademy.messages

case class SetRequest(key: String, value: Any)
case class GetRequest(key: String)
case class SetIfNotExists(key: String, value: Any)
case class Delete(key: String)
case class KeyNotFoundException(key: String) extends Exception
case class KeyExistsException(key: String) extends Exception
