package br.com.alura.linguagens.api.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoDatabase mongoDatabase() {
        final MongoClient mongoClient = mongoClient();

        return mongoClient.getDatabase("languagesdb");
    }

    @Bean
    public MongoClient mongoClient() {
        final var connectionString = new ConnectionString((System.getenv("SPRING_DATA_MONGODB_URI")));
        final var pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        final var codedRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        final var clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codedRegistry)
                .build();

        final var mongoClient = MongoClients.create(clientSettings);
        return mongoClient;
    }

}
