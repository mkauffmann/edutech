package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.service.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> enrollStudentInCourse(@RequestParam Long studentId, @RequestParam Long courseId){
        service.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<CourseDTO>> getCoursesForStudent(@PathVariable Long studentId) {
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
