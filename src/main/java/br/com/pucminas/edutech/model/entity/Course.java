package br.com.pucminas.edutech.model.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COURSE")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "COVER_IMG_URL", nullable = false)
    private String coverImgUrl;

    @ManyToMany
    @JoinTable (
            name = "course_lesson",
            joinColumns = {@JoinColumn(name = "ID_COURSE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_LESSON")}
    )
    private List<Lesson> lessons;

    public Course() {
    }

    public Course(String name, String description, String coverImgUrl, List<Lesson> lessons) {
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.lessons = lessons;
    }

    public Course(Long id, String name, String description, String coverImgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
