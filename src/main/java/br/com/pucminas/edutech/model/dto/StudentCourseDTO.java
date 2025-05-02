package br.com.pucminas.edutech.model.dto;

import java.time.LocalDateTime;

public class StudentCourseDTO {
    private Long id;
    private String studentId;
    private CourseDTO course;
    private LocalDateTime enrolledAt;

    public StudentCourseDTO(Long id, String studentId, CourseDTO course, LocalDateTime enrolledAt) {
        this.id = id;
        this.studentId = studentId;
        this.course = course;
        this.enrolledAt = enrolledAt;
    }

    public StudentCourseDTO(String studentId, CourseDTO course, LocalDateTime enrolledAt) {
        this.studentId = studentId;
        this.course = course;
        this.enrolledAt = enrolledAt;
    }

    public StudentCourseDTO() {
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

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
