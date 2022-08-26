package br.com.alura.languages.api.usecase;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.exception.LanguageNotFoundException;
import br.com.alura.languages.api.repository.LanguageRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FindAllLanguagesImpl implements FindAllLanguages{

  private final LanguageRepository repository;

  public FindAllLanguagesImpl(LanguageRepository repository) {
    this.repository = repository;
  }

  @Override
  public Uni<List<Language>> execute() throws LanguageNotFoundException {
    return repository.findAll()
        .onItem()
        .transform(languagesDB ->
            languagesDB.stream()
                .map(languageDB -> new Language(languageDB.name, languageDB.image, languageDB.ranking))
                .collect(Collectors.toList())
        );
  }
}
