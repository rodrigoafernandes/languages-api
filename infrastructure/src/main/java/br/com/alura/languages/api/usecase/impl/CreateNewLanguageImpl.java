package br.com.alura.languages.api.usecase.impl;

import br.com.alura.languages.api.database.entity.LanguageDBEntity;
import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.exception.LanguageAlreadyExistsException;
import br.com.alura.languages.api.repository.CreateNewLanguageRepository;
import br.com.alura.languages.api.usecase.CreateNewLanguage;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;

@Singleton
public class CreateNewLanguageImpl implements CreateNewLanguage {

    private final CreateNewLanguageRepository repository;

    public CreateNewLanguageImpl(CreateNewLanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Language> execute(Language newLanguage) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException {
        return Single.just(new LanguageDBEntity(newLanguage.getName(), newLanguage.getImage(), newLanguage.getRanking()))
                .flatMap(repository::execute)
                .map(createdLanguage -> newLanguage);
    }

}
