package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.exception.LanguageAlreadyExistsException;

import io.reactivex.rxjava3.core.Single;

public interface CreateNewLanguage {

    Single<Language> execute(Language newLanguage) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException;

}
