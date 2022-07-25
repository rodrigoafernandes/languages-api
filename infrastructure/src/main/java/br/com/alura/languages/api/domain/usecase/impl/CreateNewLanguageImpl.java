package br.com.alura.languages.api.domain.usecase.impl;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.database.entity.LanguageDBEntity;
import br.com.alura.languages.api.domain.exception.InvalidLanguagePayloadException;
import br.com.alura.languages.api.domain.exception.LanguageAlreadyExistsException;
import br.com.alura.languages.api.domain.usecase.CreateNewLanguage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoDatabase;
import reactor.core.publisher.Mono;

public class CreateNewLanguageImpl implements CreateNewLanguage {

    private final ObjectMapper mapper;
    private final MongoDatabase mongoDatabase;

    public CreateNewLanguageImpl(ObjectMapper mapper,
                                 MongoDatabase mongoDatabase) {
        this.mapper = mapper;
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public Mono<Language> execute(Language newLanguage) throws LanguageAlreadyExistsException, InvalidLanguagePayloadException {
        return Mono.from(mongoDatabase.getCollection("languages", LanguageDBEntity.class)
                                .insertOne(new LanguageDBEntity(newLanguage.name(), newLanguage.image(), newLanguage.ranking())))
                .map(insertOneResult -> newLanguage);
    }

}
