package br.com.alura.languages.api.entity;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Language {

    private String name;
    private String image;
    private Long ranking;

    public Language() {
    }

    public Language(String name, String image, Long ranking) {
        this.name = name;
        this.image = image;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }
}
