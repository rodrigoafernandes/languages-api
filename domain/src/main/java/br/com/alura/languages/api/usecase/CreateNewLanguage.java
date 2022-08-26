package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.exception.LanguageAlreadyExistsException;
import io.smallrye.mutiny.Uni;

public interface CreateNewLanguage {

  Uni<Language> execute(Language language) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException;

}
