package br.com.alura.languages.api.domain.usecase;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.domain.exception.LanguageAlreadyExistsException;
import reactor.core.publisher.Mono;

public interface CreateNewLanguage {

    Mono<Language> execute(Language newLanguage) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException;

}
