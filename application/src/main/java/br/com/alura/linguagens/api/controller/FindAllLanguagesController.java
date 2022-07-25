package br.com.alura.linguagens.api.controller;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.usecase.FindAllLanguages;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class FindAllLanguagesController {

    private final FindAllLanguages findAllLanguages;

    public FindAllLanguagesController(FindAllLanguages findAllLanguages) {
        this.findAllLanguages = findAllLanguages;
    }

    @GetMapping("/languages")
    Mono<List<Language>> findAllLanguages() {
        return findAllLanguages.execute();
    }
}
