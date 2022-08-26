package br.com.alura.languages.api.controller;

import br.com.alura.languages.api.entity.Language;
import br.com.alura.languages.api.usecase.FindAllLanguages;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/languages")
public class FindAllLanguagesController {

  private final FindAllLanguages findAllLanguages;

  public FindAllLanguagesController(FindAllLanguages findAllLanguages) {
    this.findAllLanguages = findAllLanguages;
  }

  @GET
  @Produces(APPLICATION_JSON)
  public Uni<List<Language>> findAll() {
    return findAllLanguages.execute();
  }
}
