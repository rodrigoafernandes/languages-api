package br.com.alura.languages.api.repository;

import br.com.alura.languages.api.database.entity.LanguageDatabaseEntity;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface LanguageRepository {

  Uni<List<LanguageDatabaseEntity>> findAll();

  Uni<LanguageDatabaseEntity> createNewLanguage(LanguageDatabaseEntity languageDatabase);

}
