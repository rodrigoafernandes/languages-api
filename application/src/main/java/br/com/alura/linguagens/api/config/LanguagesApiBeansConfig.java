package br.com.alura.linguagens.api.config;

import br.com.alura.languages.api.domain.usecase.CreateNewLanguage;
import br.com.alura.languages.api.domain.usecase.FindAllLanguages;
import br.com.alura.languages.api.domain.usecase.impl.CreateNewLanguageImpl;
import br.com.alura.languages.api.domain.usecase.impl.FindAllLanguagesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguagesApiBeansConfig {

    @Bean
    public CreateNewLanguage createNewLanguage(ObjectMapper mapper,
                                               MongoDatabase mongoDatabase) {
        return new CreateNewLanguageImpl(mapper, mongoDatabase);
    }

    @Bean
    public FindAllLanguages findAllLanguages(MongoDatabase mongoDatabase) {
        return new FindAllLanguagesImpl(mongoDatabase);
    }

}
