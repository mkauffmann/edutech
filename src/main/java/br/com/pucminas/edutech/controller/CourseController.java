package br.com.pucminas.edutech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.service.CourseService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
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
    public ResponseEntity<CourseDTO> updateCourseInfo(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(service.updateCourseInfo(id, courseDTO));
    }

    @PutMapping()
    public ResponseEntity<CourseDTO> addLessonToCourse(@RequestParam Long courseId, @RequestParam Long lessonId) {
        return ResponseEntity.ok(service.addLessonToCourse(courseId, lessonId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
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
