package br.com.alura.linguagens.api.health;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Checker implements ReactiveHealthIndicator {

    private final MongoClient mongoClient;

    public Checker(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Mono<Health> health() {
        return Flux.from(mongoClient.listDatabaseNames())
                .collectList()
                .map(collections -> new Health.Builder().up().build())
                .onErrorResume(throwable -> Mono.just(new Health.Builder().down(throwable).build()));
    }
}
