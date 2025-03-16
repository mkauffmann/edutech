package br.com.pucminas.edutech.model.dto;

import java.time.LocalDateTime;

public class StudentPointsDTO {
    private Long studentId;
    private Integer points;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentPointsDTO() {
    }

    public StudentPointsDTO(Long studentId, Integer points, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.studentId = studentId;
        this.points = points;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public StudentPointsDTO(Integer points) {
        this.points = points;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
