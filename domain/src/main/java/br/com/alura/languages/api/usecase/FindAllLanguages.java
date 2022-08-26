package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.LanguageNotFoundException;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface FindAllLanguages {

  Uni<List<Language>> execute() throws LanguageNotFoundException;

}
