package serverless

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ServerlessTsInversifyJSMultiTransmission extends Simulation {

  val httpConfig = http
    .baseURL(
      "https://84s0gzk2jj.execute-api.ap-northeast-1.amazonaws.com/dev"
    )
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    )
    .doNotTrackHeader("1");

  val scn = scenario("AWS Lambda (InversifyJS) - Multi request transmission.").exec(
    http("Montecalro InversifyJS").get("/montecarlo-inversify").check(status.is(200))
  );

  setUp(scn.inject(constantUsersPerSec(50) during (200 seconds)))
    .protocols(httpConfig);
}
