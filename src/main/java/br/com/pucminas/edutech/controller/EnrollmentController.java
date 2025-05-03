package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.service.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> enrollStudentInCourse(@AuthenticationPrincipal Jwt jwt, @RequestParam Long courseId){
        String studentId = jwt.getClaimAsString("preferred_username");

        service.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<CourseDTO>> getCoursesForStudent(@AuthenticationPrincipal Jwt jwt) {
        String studentId = jwt.getClaimAsString("preferred_username");

        return ResponseEntity.ok(service.getCoursesForStudent(studentId));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
