package br.com.pucminas.edutech.model.dto;


public class LessonDTO {
    private Long id;
    private String name;
    private String description;
    private String videoUrl;

    public LessonDTO() {
    }

    public LessonDTO(String name, String description, String videoUrl) {
        this.name = name;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public LessonDTO(Long id, String name, String description, String videoUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.videoUrl = videoUrl;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
