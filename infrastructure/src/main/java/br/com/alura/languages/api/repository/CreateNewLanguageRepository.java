package br.com.alura.languages.api.repository;

import br.com.alura.languages.api.database.entity.LanguageDBEntity;
import io.reactivex.rxjava3.core.Single;

public interface CreateNewLanguageRepository {

    Single<LanguageDBEntity> execute(LanguageDBEntity newLanguage);

}
