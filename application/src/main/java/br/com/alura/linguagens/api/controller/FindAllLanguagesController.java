package br.com.alura.linguagens.api.controller;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.usecase.FindAllLanguages;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

@Controller("/languages")
public class FindAllLanguagesController {

    private final FindAllLanguages findAllLanguages;

    public FindAllLanguagesController(FindAllLanguages findAllLanguages) {
        this.findAllLanguages = findAllLanguages;
    }

    @Get
    Single<List<Language>> findAllLanguages() {
        return findAllLanguages.execute();
    }
}
