package br.com.pucminas.edutech.model.dto;

import java.time.LocalDateTime;

public class StudentBadgeDTO {
    private Long id;
    private Long studentId;  // Foreign key to student service
    private BadgeDTO badge;
    private LocalDateTime createdAt;

    public StudentBadgeDTO() {
    }

    public StudentBadgeDTO(Long id, Long studentId, BadgeDTO badge, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.badge = badge;
        this.createdAt = createdAt;
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

    public BadgeDTO getBadge() {
        return badge;
    }

    public void setBadge(BadgeDTO badge) {
        this.badge = badge;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
