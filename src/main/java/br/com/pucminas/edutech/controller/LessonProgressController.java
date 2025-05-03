package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.LessonDTO;
import br.com.pucminas.edutech.service.LessonProgressService;
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
@RequestMapping("/lessonProgress")
public class LessonProgressController {
    private final LessonProgressService service;

    public LessonProgressController(LessonProgressService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> markLessonAsWatched(@AuthenticationPrincipal Jwt jwt, @RequestParam Long lessonId){
        String studentId = jwt.getClaimAsString("preferred_username");

        service.markLessonAsWatched(studentId, lessonId);
        return ResponseEntity.ok("Lesson watched");
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<LessonDTO>> getLessonsWatchedByStudent(@AuthenticationPrincipal Jwt jwt){
        String studentId = jwt.getClaimAsString("preferred_username");

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
