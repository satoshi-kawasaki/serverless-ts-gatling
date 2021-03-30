package serverless

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ServerlessTsTSyringe extends Simulation {

  val httpConfig = http
    .baseURL(
      "https://84s0gzk2jj.execute-api.ap-northeast-1.amazonaws.com/dev"
    )
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    )
    .doNotTrackHeader("1");

  val scn = scenario("TSyringeを使って実装したAWS Lambdaの性能測定").exec(
    http("Montecalro TSyringe").get("/montecarlo-tsyringe").check(status.is(200))
  );

  setUp(scn.inject(constantUsersPerSec(1) during (1000 seconds)))
    .protocols(httpConfig);
}
