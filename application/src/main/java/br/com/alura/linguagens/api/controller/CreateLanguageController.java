package br.com.alura.linguagens.api.controller;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.usecase.CreateNewLanguage;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.rxjava3.core.Single;

import java.net.URI;

@Controller("/languages")
public class CreateLanguageController {

    private final CreateNewLanguage createNewLanguage;

    public CreateLanguageController(CreateNewLanguage createNewLanguage) {
        this.createNewLanguage = createNewLanguage;
    }

    @Post
    Single<HttpResponse<Void>> createLanguage(HttpRequest<?> request,
                                              @Body Language newLanguage) {
        return createNewLanguage.execute(newLanguage)
                .map(language ->
                        HttpResponse.created(URI.create(request.getUri().toASCIIString()
                                .concat("/").concat(language.getName()))));
    }

}
