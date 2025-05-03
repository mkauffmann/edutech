package br.com.pucminas.edutech.model.dto;

import java.time.LocalDateTime;

public class StudentLessonProgressDTO {
    private Long id;
    private String studentId;
    private LessonDTO lesson;
    private LocalDateTime watchedAt;

    public StudentLessonProgressDTO(Long id, String studentId, LessonDTO lesson, LocalDateTime watchedAt) {
        this.id = id;
        this.studentId = studentId;
        this.lesson = lesson;
        this.watchedAt = watchedAt;
    }

    public StudentLessonProgressDTO(String studentId, LessonDTO lesson, LocalDateTime watchedAt) {
        this.studentId = studentId;
        this.lesson = lesson;
        this.watchedAt = watchedAt;
    }

    public StudentLessonProgressDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }

    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }
}
