package br.com.alura.languages.api.entity;

public class Language {

  private String name;
  private String image;
  private Long ranking;

  public Language(String name, String image, Long ranking) {
    this.name = name;
    this.image = image;
    this.ranking = ranking;
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  public Long getRanking() {
    return ranking;
  }

}
