package br.com.pucminas.edutech.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_lessons_progress")
public class StudentLessonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "watched_at", nullable = false)
    private LocalDateTime watchedAt;

    public StudentLessonProgress(Long id, Long studentId, Lesson lesson, LocalDateTime watchedAt) {
        this.id = id;
        this.studentId = studentId;
        this.lesson = lesson;
        this.watchedAt = watchedAt;
    }

    public StudentLessonProgress(Long studentId, Lesson lesson, LocalDateTime watchedAt) {
        this.studentId = studentId;
        this.lesson = lesson;
        this.watchedAt = watchedAt;
    }

    public StudentLessonProgress() {
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }

    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }
}
