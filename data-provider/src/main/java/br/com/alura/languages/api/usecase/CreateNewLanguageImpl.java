package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.database.entity.LanguageDatabaseEntity;
import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.exception.LanguageAlreadyExistsException;
import br.com.alura.languages.api.repository.LanguageRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateNewLanguageImpl implements CreateNewLanguage {

  private final LanguageRepository languageRepository;

  public CreateNewLanguageImpl(LanguageRepository languageRepository) {
    this.languageRepository = languageRepository;
  }

  @Override
  public Uni<Language> execute(Language language) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException {
    return Uni.createFrom().item(LanguageDatabaseEntity.fromEntity(language))
        .onItem()
        .transformToUni(languageRepository::createNewLanguage)
        .onItem()
        .transform(languageDatabase -> language);
  }
}
