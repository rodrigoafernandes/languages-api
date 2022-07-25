package br.com.alura.languages.api.domain.database.entity;

import org.bson.types.ObjectId;

public class LanguageDBEntity {

    private ObjectId id;
    private String name;
    private String image;
    private Long ranking;

    public LanguageDBEntity() {}

    public LanguageDBEntity(ObjectId id, String name, String image, Long ranking) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.ranking = ranking;
    }

    public LanguageDBEntity(String name, String image, Long ranking) {
        this(null, name, image, ranking);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
