package com.reactivebbq.customers.json

import com.reactivebbq.customers.CreateCustomerDTO
import org.scalatest.flatspec._
import org.scalatest.matchers._
import spray.json.{JsonParser, _}

import scala.language.implicitConversions
import com.reactivebbq.customers.CustomerJsonProtocol._
class JsonTest extends AnyFlatSpec with should.Matchers {

  "Un/marshalling methods" should "transform a String into a json object and viceversa" in {
    val source = """{ "key": "value" }"""
    //unmarshal:
    val jsonAst: JsValue = JsonParser(source) // or:
    val jsonast = source.parseJson //(equivalent)
    jsonast should equal(jsonAst)

    //marshal:
    val jsonString = jsonAst.prettyPrint // or:
    val jsonString_1 = jsonAst.compactPrint
    println(s"json: $jsonString_1")
    jsonString.replaceAll("[\n ]+", "") should equal(jsonString_1)
  }

  "Json Protocols" should "enable converting Scala objects to a JSON AST and viceversa" in {
    val customer = CreateCustomerDTO("username", "fullName", "email", "phoneNumber", "address")
    val jsValue = customer.toJson
    val stringJson = jsValue.compactPrint
    println(s"customer json string: $stringJson")
    val item = jsValue.convertTo[CreateCustomerDTO]
    item should equal(customer)
  }
}
