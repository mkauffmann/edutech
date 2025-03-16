package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.BadgeDTO;
import br.com.pucminas.edutech.service.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
public class BadgeController {
    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @PostMapping
    public ResponseEntity<BadgeDTO> createUser(@RequestBody BadgeDTO badgeDTO) {
        return new ResponseEntity<>(badgeService.createBadge(badgeDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BadgeDTO>> getAllBadges() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeDTO> getBadgeById(@PathVariable Long id) {
        return ResponseEntity.ok(badgeService.getBadgeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BadgeDTO> updateBadge(@PathVariable Long id, @RequestBody BadgeDTO badgeDTO) {
        return ResponseEntity.ok(badgeService.updateBadge(id, badgeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable Long id) {
        badgeService.deleteBadge(id);
        return ResponseEntity.noContent().build();
    }

}
