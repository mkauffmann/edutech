package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.StudentPointsDTO;
import br.com.pucminas.edutech.service.StudentPointsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
public class StudentPointsController {
    private final StudentPointsService service;

    public StudentPointsController(StudentPointsService service) {
        this.service = service;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentPointsDTO> updatePoints(@PathVariable Long studentId, @RequestBody StudentPointsDTO dto) {
        return ResponseEntity.ok(service.updatePoints(studentId, dto.getPoints()));
    }

    @GetMapping
    public List<StudentPointsDTO> getStudentPointsRanking(){
        return service.getStudentPointsRanking();
    }
}
