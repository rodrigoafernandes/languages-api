package br.com.alura.languages.api.repository;

import br.com.alura.languages.api.database.entity.LanguageDBEntity;
import io.micronaut.cache.annotation.CachePut;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;

@Singleton
public class FindAllLanguagesRepositoryImpl implements FindAllLanguagesRepository {

    private final LanguageRepository repository;

    public FindAllLanguagesRepositoryImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    @CachePut("languages-cache")
    public Flowable<LanguageDBEntity> execute() {
        return Flowable.fromPublisher(repository.findAll());
    }
}
