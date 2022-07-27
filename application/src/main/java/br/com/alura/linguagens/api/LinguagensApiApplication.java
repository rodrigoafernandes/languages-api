package br.com.alura.linguagens.api;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "languages-api",
				version = "0.0"
		)
)
public class LinguagensApiApplication {

	public static void main(String[] args) {
		Micronaut.build(args).banner(false).start();
	}

}
