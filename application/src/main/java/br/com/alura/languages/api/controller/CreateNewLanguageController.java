package br.com.alura.languages.api.controller;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.usecase.CreateNewLanguage;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/languages")
public class CreateNewLanguageController {

  private final CreateNewLanguage createNewLanguage;

  public CreateNewLanguageController(CreateNewLanguage createNewLanguage) {
    this.createNewLanguage = createNewLanguage;
  }

  @POST
  @Produces(APPLICATION_JSON)
  public Uni<Response> createNewLanguage(@Context UriInfo uriInfo,
                                         @RequestBody Language language) {
    return createNewLanguage.execute(language)
        .onItem()
        .transform(newLanguage ->
            Response.created(UriBuilder.fromPath(uriInfo.getPath()).path("/{name}").build(newLanguage.getName())).build()
        );
  }

}
