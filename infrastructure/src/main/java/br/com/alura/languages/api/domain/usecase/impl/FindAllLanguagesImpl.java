package br.com.alura.languages.api.domain.usecase.impl;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.database.entity.LanguageDBEntity;
import br.com.alura.languages.api.domain.exception.LanguageNotFoundException;
import br.com.alura.languages.api.domain.usecase.FindAllLanguages;
import com.mongodb.reactivestreams.client.MongoDatabase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FindAllLanguagesImpl implements FindAllLanguages {

    private final MongoDatabase mongoDatabase;

    public FindAllLanguagesImpl(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public Mono<List<Language>> execute() throws LanguageNotFoundException {
        return Flux.from(mongoDatabase.getCollection("languages", LanguageDBEntity.class).find())
                .map(languageDB -> new Language(languageDB.getName(), languageDB.getImage(), languageDB.getRanking()))
                .collectList();
    }
}
