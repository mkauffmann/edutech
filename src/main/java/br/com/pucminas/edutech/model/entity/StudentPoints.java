package br.com.pucminas.edutech.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "students_points")
public class StudentPoints {

    @Id
    @Column(name = "student_id")
    private String studentId;  // Foreign key to student service

    @Column(name = "points")
    private int points;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public StudentPoints(LocalDateTime updatedAt, LocalDateTime createdAt, int points, String studentId) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.points = points;
        this.studentId = studentId;
    }

    public StudentPoints(String studentId, LocalDateTime createdAt) {
        this.studentId = studentId;
        this.points = 0;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public StudentPoints() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
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
