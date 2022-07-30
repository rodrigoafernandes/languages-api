package br.com.alura.languages.api;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class Engine {

  public static void main(String[] args) {
      final var props = new GatlingPropertiesBuilder()
          .resourcesDirectory(PathHelper.gradleResourcesDirectory)
          .resultsDirectory(PathHelper.resultsDirectory)
          .binariesDirectory(PathHelper.gradleBinariesDirectory);

      Gatling.fromMap(props.build());
  }

}
