package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.StudentPointsDTO;
import br.com.pucminas.edutech.service.StudentPointsService;
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
@RequestMapping("/points")
public class StudentPointsController {
    private final StudentPointsService service;

    public StudentPointsController(StudentPointsService service) {
        this.service = service;
    }

    @PutMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentPointsDTO> updatePoints(@AuthenticationPrincipal Jwt jwt, @RequestBody StudentPointsDTO dto) {
        String studentId = jwt.getClaimAsString("preferred_username");

        return ResponseEntity.ok(service.updatePoints(studentId, dto.getPoints()));
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<StudentPointsDTO>> getStudentPointsRanking(){
        return ResponseEntity.ok(service.getStudentPointsRanking());
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentPointsDTO> getStudentPointsByID(@AuthenticationPrincipal Jwt jwt){
        String studentId = jwt.getClaimAsString("preferred_username");

        return ResponseEntity.ok(service.getStudentPointsByID(studentId));
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> clearStudentPoints(@PathVariable String studentId){
        service.clearStudentPoints(studentId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
