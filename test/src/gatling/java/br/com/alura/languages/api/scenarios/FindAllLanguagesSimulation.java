package br.com.alura.languages.api.scenarios;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class FindAllLanguagesSimulation extends Simulation {

  ChainBuilder getAllLanguages = exec(
      http("GET").get(System.getenv("LANGUAGES_API_URI"))
          .check(status().is(200))
  );

  ScenarioBuilder scenario = scenario("All Languages")
      .exec(getAllLanguages);

  {
    setUp(
        scenario.injectOpen(constantUsersPerSec(200).during(300))
    );
  }
}
