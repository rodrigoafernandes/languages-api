package br.com.alura.linguagens.api.health;

import br.com.alura.languages.api.repository.FindAllLanguagesRepository;
import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import io.micronaut.management.health.indicator.annotation.Readiness;
import org.reactivestreams.Publisher;

@Readiness
public class LanguageRepositoryHealthIndicator implements HealthIndicator {

    private final FindAllLanguagesRepository findAllLanguages;

    public LanguageRepositoryHealthIndicator(FindAllLanguagesRepository findAllLanguages) {
        this.findAllLanguages = findAllLanguages;
    }

    @Override
    public Publisher<HealthResult> getResult() {
        return findAllLanguages.execute()
                .map(languages -> HealthResult.builder("mongodb", HealthStatus.UP).build())
                .onErrorReturnItem(HealthResult.builder("mongodb", HealthStatus.DOWN).build());
    }
}
