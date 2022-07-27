package br.com.alura.languages.api.usecase.impl;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.LanguageNotFoundException;
import br.com.alura.languages.api.repository.FindAllLanguagesRepository;
import br.com.alura.languages.api.usecase.FindAllLanguages;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class FindAllLanguagesImpl implements FindAllLanguages {

    private final FindAllLanguagesRepository repository;

    public FindAllLanguagesImpl(FindAllLanguagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Language>> execute() throws LanguageNotFoundException {
        return repository.execute()
                .map(languageDB -> new Language(languageDB.getName(), languageDB.getImage(), languageDB.getRanking()))
                .toList();
    }
}
