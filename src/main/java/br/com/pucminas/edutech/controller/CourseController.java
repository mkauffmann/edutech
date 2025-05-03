package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(service.createCourse(courseDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> updateCourseInfo(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(service.updateCourseInfo(id, courseDTO));
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> addLessonToCourse(@RequestParam Long courseId, @RequestParam Long lessonId) {
        return ResponseEntity.ok(service.addLessonToCourse(courseId, lessonId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDTO> deleteLessonFromCourse(@RequestParam Long courseId, @RequestParam Long lessonId) {
        return ResponseEntity.ok(service.deleteLessonFromCourse(courseId, lessonId));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
