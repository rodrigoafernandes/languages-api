package br.com.alura.languages.api.database.entity;

import br.com.alura.languages.api.entity.Language;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class LanguageDatabaseEntity extends ReactivePanacheMongoEntity {
  public String name;
  public String image;
  public Long ranking;

  public static LanguageDatabaseEntity fromEntity(Language language) {
    final var languageDatabase = new LanguageDatabaseEntity();

    languageDatabase.name = language.getName();
    languageDatabase.image = language.getImage();
    languageDatabase.ranking = language.getRanking();

    return languageDatabase;
  }
}
