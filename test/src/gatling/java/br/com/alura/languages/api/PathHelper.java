package br.com.alura.languages.api;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathHelper {

  static final String gradleSourcesDirectory;
  static final String gradleResourcesDirectory;
  static final String gradleBinariesDirectory;
  static final String resultsDirectory;
  static final Path recorderConfigFile;

  static {
    try {
      final var projectRootDir = Paths.get(
              PathHelper.class.getClassLoader().getResource("gatling.conf").toURI())
          .getParent().getParent().getParent().getParent();
      final var gradleSrcDirectory = projectRootDir.resolve("src").resolve("gatling");
      final var gradleBuildDirectory = projectRootDir.resolve("build");

      gradleSourcesDirectory = gradleSrcDirectory.resolve("java").toString();
      gradleResourcesDirectory = gradleSrcDirectory.resolve("resources").toString();
      gradleBinariesDirectory = gradleBuildDirectory.resolve("classes").resolve("java").resolve("gatling").toString();
      resultsDirectory = gradleBuildDirectory.resolve("reports").resolve("gatling").toString();
      recorderConfigFile = gradleSrcDirectory.resolve("resources").resolve("recorder.conf");
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

}
