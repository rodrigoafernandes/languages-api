package br.com.alura.languages.api.repository;

import br.com.alura.languages.api.database.entity.LanguageDBEntity;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;

@Singleton
public class CreateNewLanguageRepositoryImpl implements CreateNewLanguageRepository {

    private final LanguageRepository repository;

    public CreateNewLanguageRepositoryImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<LanguageDBEntity> execute(LanguageDBEntity newLanguage) {
        return Single.fromPublisher(repository.save(newLanguage));
    }
}
