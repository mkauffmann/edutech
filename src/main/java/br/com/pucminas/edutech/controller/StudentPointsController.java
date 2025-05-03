package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.StudentPointsDTO;
import br.com.pucminas.edutech.service.StudentPointsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentPointsDTO> updatePoints(@PathVariable Long studentId, @RequestBody StudentPointsDTO dto) {
        return ResponseEntity.ok(service.updatePoints(studentId, dto.getPoints()));
    }

    @GetMapping
    public ResponseEntity<List<StudentPointsDTO>> getStudentPointsRanking(){
        return ResponseEntity.ok(service.getStudentPointsRanking());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentPointsDTO> getStudentPointsByID(@PathVariable Long studentId){
        return ResponseEntity.ok(service.getStudentPointsByID(studentId));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> clearStudentPoints(@PathVariable Long studentId){
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
