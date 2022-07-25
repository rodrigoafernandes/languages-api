package br.com.alura.linguagens.api.controller;

import br.com.alura.languages.api.domain.Language;
import br.com.alura.languages.api.domain.exception.LanguageAlreadyExistsException;
import br.com.alura.languages.api.domain.usecase.CreateNewLanguage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
public class CreateLanguageController {

    private final CreateNewLanguage createNewLanguage;

    public CreateLanguageController(CreateNewLanguage createNewLanguage) {
        this.createNewLanguage = createNewLanguage;
        new ObjectMapper();
    }

    @PostMapping("/languages")
    Mono<ResponseEntity<Object>> createLanguage(ServerHttpRequest request, @RequestBody Language newLanguage) {
        return createNewLanguage.execute(newLanguage)
                .map(language ->
                        ResponseEntity.created(UriComponentsBuilder.fromHttpRequest(request)
                                        .path("/{name}").buildAndExpand(language.name()).toUri()).build())
                .doOnError(LanguageAlreadyExistsException.class, e -> ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build());
    }

}
