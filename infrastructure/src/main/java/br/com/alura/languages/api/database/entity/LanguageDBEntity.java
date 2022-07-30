package br.com.alura.languages.api.database.entity;

import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Cacheable
@Introspected
@MappedEntity("languages")
public class LanguageDBEntity implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private String image;

    @NonNull
    @Min(1)
    private Long ranking;

    public LanguageDBEntity(String name, String image, Long ranking) {
        this.name = name;
        this.image = image;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    @NonNull
    public Long getRanking() {
        return ranking;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    public void setRanking(@NonNull Long ranking) {
        this.ranking = ranking;
    }
}
