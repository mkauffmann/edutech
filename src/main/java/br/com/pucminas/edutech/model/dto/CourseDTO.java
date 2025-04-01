package br.com.pucminas.edutech.model.dto;

import java.util.List;

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private String coverImgUrl;
    private List<ClassDTO> classes;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, String coverImgUrl, List<ClassDTO> classes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.classes = classes;
    }

    public CourseDTO(String name, String description, String coverImgUrl, List<ClassDTO> classes) {
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.classes = classes;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public List<ClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassDTO> classes) {
        this.classes = classes;
    }
}
