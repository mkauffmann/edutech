package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.StudentBadgeDTO;
import br.com.pucminas.edutech.service.StudentBadgeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studentBadge")
public class StudentBadgeController {
    private final StudentBadgeService service;


    public StudentBadgeController(StudentBadgeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentBadgeDTO> assignBadgeToStudent(@RequestParam Long studentId, @RequestParam Long badgeId){
        return ResponseEntity.ok(service.assignBadgeToStudent(studentId, badgeId));
    }

    @GetMapping
    public ResponseEntity<List<StudentBadgeDTO>> getBadgesByStudentId(@RequestParam Long studentId) {
        return ResponseEntity.ok(service.getBadgesByStudentId(studentId));
    }


    @DeleteMapping
    public ResponseEntity<String> deleteStudentBadge(@RequestParam Long studentId, @RequestParam Long badgeId) {
        service.deleteStudentBadge(studentId, badgeId);
        return ResponseEntity.ok("StudentBadge deleted successfully.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
