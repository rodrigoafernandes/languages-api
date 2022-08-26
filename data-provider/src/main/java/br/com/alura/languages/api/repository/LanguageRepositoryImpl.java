package br.com.alura.languages.api.repository;

import br.com.alura.languages.api.database.entity.LanguageDatabaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Command;
import io.vertx.mutiny.redis.client.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class LanguageRepositoryImpl implements LanguageRepository {

  private final Logger LOGGER = Logger.getLogger(LanguageRepository.class);

  private final ReactiveRedisDataSource redisDataSource;
  private final ObjectMapper mapper;

  public LanguageRepositoryImpl(ReactiveRedisDataSource redisDataSource,
                                ObjectMapper mapper) {
    this.redisDataSource = redisDataSource;
    this.mapper = mapper;
  }

  @Override
  public Uni<List<LanguageDatabaseEntity>> findAll() {
    return redisDataSource.getRedis()
        .send(Request.cmd(Command.GET).arg("languages"))
        .onItem()
        .ifNotNull()
        .transformToUni(response -> {
          LOGGER.info("Reading languages from Cache");
          final var languages = Collections.synchronizedList(new ArrayList<LanguageDatabaseEntity>());
          try {
            languages.addAll(mapper.readValue(response.toString(), new TypeReference<List<LanguageDatabaseEntity>>() {}));
          } catch (JsonProcessingException e) {}

          return CollectionUtils.isEmpty(languages) ? Uni.createFrom().nullItem() : Uni.createFrom().item(languages);
        })
        .onItem()
        .ifNull()
        .switchTo(() -> {
          LOGGER.info("Reading languages from DB");
          return LanguageDatabaseEntity.listAll();
        })
        .onItem()
        .ifNotNull()
        .call(languages -> {
          try {
            LOGGER.info("Putting languages on cache");
            return Uni.combine().all()
                .unis(
                    redisDataSource.getRedis().send(Request.cmd(Command.SET).arg("languages").arg(mapper.writeValueAsBytes(languages))),
                    Uni.createFrom().item(languages)
                ).asTuple();
          } catch (JsonProcessingException e) {
            return Uni.createFrom().failure(e);
          }
        });
  }

  @Override
  public Uni<LanguageDatabaseEntity> createNewLanguage(LanguageDatabaseEntity languageDatabase) {
    return languageDatabase.persist()
        .onItem()
        .transform(dbEntity -> languageDatabase)
        .call(() ->
            redisDataSource.getRedis()
                .send(Request.cmd(Command.DEL).arg("languages"))
                .onItem()
                .transform(response -> languageDatabase)
        );
  }
}
