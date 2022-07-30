package br.com.alura.languages.api;

import io.gatling.recorder.GatlingRecorder;
import io.gatling.recorder.config.RecorderPropertiesBuilder;
import scala.Option;

public class Recorder {
  public static void main(String[] args) {
    final var props = new RecorderPropertiesBuilder()
        .simulationsFolder(PathHelper.gradleSourcesDirectory)
        .resourcesFolder(PathHelper.gradleResourcesDirectory)
        .simulationPackage("br/com/alura/languages/api/scenarios")
        .simulationFormatJava();

    GatlingRecorder.fromMap(props.build(), Option.apply(PathHelper.recorderConfigFile));
  }
}
