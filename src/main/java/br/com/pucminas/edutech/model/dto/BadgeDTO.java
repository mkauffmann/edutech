package br.com.pucminas.edutech.model.dto;


public class BadgeDTO {
    private Long id;
    private String name;
    private String imgUrl;

    public BadgeDTO(Long id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public BadgeDTO(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public BadgeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
