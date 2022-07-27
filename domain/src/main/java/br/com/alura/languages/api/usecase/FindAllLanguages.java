package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.LanguageNotFoundException;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface FindAllLanguages {

    Single<List<Language>> execute() throws LanguageNotFoundException;

}
