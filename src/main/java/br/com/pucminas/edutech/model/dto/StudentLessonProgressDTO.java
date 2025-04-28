package br.com.pucminas.edutech.model.dto;

import java.time.LocalDateTime;

public class StudentLessonProgressDTO {
    private Long id;
    private Long studentId;
    private LessonDTO lesson;
    private LocalDateTime watchedAt;

    public StudentLessonProgressDTO(Long id, Long studentId, LessonDTO lesson, LocalDateTime watchedAt) {
        this.id = id;
        this.studentId = studentId;
        this.lesson = lesson;
        this.watchedAt = watchedAt;
    }

    public StudentLessonProgressDTO(Long studentId, LessonDTO lesson, LocalDateTime watchedAt) {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
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
