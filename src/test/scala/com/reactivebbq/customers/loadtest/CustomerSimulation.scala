package com.reactivebbq.customers.loadtest

import com.reactivebbq.customers.CreateCustomerDTO
import spray.json._

import scala.language.implicitConversions
import com.reactivebbq.customers.CustomerJsonProtocol._
import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import faker._
import io.gatling.core.session.Expression

import scala.concurrent.duration._

class CustomerSimulation extends Simulation {

  lazy val urlString = "http://127.0.0.1:9551"

  private lazy val httpConfig = http.baseUrl(s"$urlString")

  private lazy val headers = Map(
    "accept-language" -> "en-US,en;q=0.9,it;q=0.8",
    "content-type" -> "application/json",
    "accept" -> "application/json, text/plain, */*")

  def generateJson(): String = {
    val fullName = Name.name
    val username = Internet.user_name(fullName)
    val email = Internet.free_email(fullName)
    val phoneNumber = PhoneNumber.phone_number
    val address = Address.street_address(false)
    val customer = CreateCustomerDTO(username, fullName, email, phoneNumber, address)
    val jsValue = customer.toJson
    jsValue.compactPrint
  }

  /** Used to create random blueprints (see customer.json) */
  lazy val generateCustomer: Expression[Session] = (s: _root_.io.gatling.core.session.Session) => {
    val json = generateJson()
    println(s"customer: $json")
    s.set("customer", json)
  }

  private lazy val createCustomer = http("create customer")
    .post("/customers")
    .headers(headers)
    .body(ElFileBody("customer.txt")).asJson

  setUp(scenario("customer creations")
    .exec(generateCustomer)
    .exec(createCustomer)
    .inject(
      //      constantUsersPerSec(2).during(10.seconds)
      rampUsersPerSec(1) to 5 during 10.seconds
    )
    .protocols(httpConfig))
}
