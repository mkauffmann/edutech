package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.LessonDTO;
import br.com.pucminas.edutech.service.LessonProgressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lessonProgress")
public class LessonProgressController {
    private final LessonProgressService service;

    public LessonProgressController(LessonProgressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> markLessonAsWatched(@RequestParam Long studentId, @RequestParam Long lessonId){
        service.markLessonAsWatched(studentId, lessonId);
        return ResponseEntity.ok("Lesson watched");
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<LessonDTO>> getLessonsWatchedByStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(service.getLessonsWatchedByStudent(studentId));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
