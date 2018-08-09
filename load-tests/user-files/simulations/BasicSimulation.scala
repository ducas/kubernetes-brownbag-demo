package sampleloadtests // 1

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BasicSimulation extends Simulation {

  val baseUrl = sys.env.getOrElse("BASEURL", "http://localhost")
  val endpoint = sys.env.getOrElse("ENDPOINT", "/")
  val rampTo = sys.env.getOrElse("USERS", "1").toInt
  val hostHeader = sys.env.getOrElse("HOST_HEADER", "")

  object Hit {
    val hit = forever(
      exec(
        http("endpoint")
        .get(endpoint)
        .check(status.in(200, 304))
      )
      .pause(1)
    )
  }

  val httpProtocol = http
    .baseURL(baseUrl)
    .disableCaching
    .header("Host", hostHeader)
    .inferHtmlResources(BlackList(), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,en-AU;q=0.8")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36")

  val users = scenario("Users").exec(Hit.hit)

  setUp(
    users.inject(rampUsers(rampTo) over(30 seconds))
  )
  .protocols(httpProtocol)
  .maxDuration(5 minutes)
}
