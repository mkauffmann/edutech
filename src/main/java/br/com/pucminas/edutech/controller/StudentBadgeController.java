package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.StudentBadgeDTO;
import br.com.pucminas.edutech.service.StudentBadgeService;
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
@RequestMapping("/studentBadge")
public class StudentBadgeController {
    private final StudentBadgeService service;


    public StudentBadgeController(StudentBadgeService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentBadgeDTO> assignBadgeToStudent(@AuthenticationPrincipal Jwt jwt, @RequestParam Long badgeId){
        String studentId = jwt.getClaimAsString("preferred_username");

        return ResponseEntity.ok(service.assignBadgeToStudent(studentId, badgeId));
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<StudentBadgeDTO>> getBadgesByStudentId(@AuthenticationPrincipal Jwt jwt) {
        String studentId = jwt.getClaimAsString("preferred_username");

        return ResponseEntity.ok(service.getBadgesByStudentId(studentId));
    }


    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudentBadge(@RequestParam String studentId, @RequestParam Long badgeId) {
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
