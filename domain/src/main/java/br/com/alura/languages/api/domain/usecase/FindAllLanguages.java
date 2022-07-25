package br.com.alura.languages.api.domain.usecase;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.exception.LanguageNotFoundException;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FindAllLanguages {

    Mono<List<Language>> execute() throws LanguageNotFoundException;

}
