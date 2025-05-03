package br.com.pucminas.edutech.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_badges")
public class StudentBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id",  nullable = false)
    private String studentId;  // Foreign key to student service

    @JoinColumn(name = "badge_id", nullable = false)
    @ManyToOne
    private Badge badge;

    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;


    public StudentBadge(String studentId, Badge badges, LocalDateTime createdAt) {
        this.studentId = studentId;
        this.badge = badges;
        this.createdAt = createdAt;
    }

    public StudentBadge(Long id, String studentId, Badge badge, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.badge = badge;
        this.createdAt = createdAt;
    }

    public StudentBadge() {
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

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
