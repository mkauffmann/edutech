package br.com.pucminas.edutech.model.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private String coverImgUrl;
    private List<LessonDTO> lessons;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, String coverImgUrl, List<LessonDTO> lessons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.lessons = lessons;
    }

    public CourseDTO(String name, String description, String coverImgUrl, List<LessonDTO> lessons) {
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.lessons = lessons;
    }

    public CourseDTO(Long id, String name, String description, String coverImgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.lessons = new ArrayList<LessonDTO>();
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

    public List<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }
}
