package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.StudentBadgeDTO;
import br.com.pucminas.edutech.model.entity.Badge;
import br.com.pucminas.edutech.model.entity.StudentBadge;
import br.com.pucminas.edutech.repository.BadgeRepository;
import br.com.pucminas.edutech.repository.StudentBadgeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentBadgeService {

    private final StudentBadgeRepository studentBadgeRepository;
    private final BadgeRepository badgeRepository;
    private final ModelMapper modelMapper;

    public StudentBadgeService(StudentBadgeRepository studentBadgeRepository, BadgeRepository badgeRepository, ModelMapper modelMapper) {
        this.studentBadgeRepository = studentBadgeRepository;
        this.badgeRepository = badgeRepository;
        this.modelMapper = modelMapper;
    }

    // Convert Entity -> DTO
    private StudentBadgeDTO convertToDTO(StudentBadge studentBadge) {
        return modelMapper.map(studentBadge, StudentBadgeDTO.class);
    }

    // Convert DTO -> Entity
    private StudentBadge convertToEntity(StudentBadgeDTO dto) {
        return modelMapper.map(dto, StudentBadge.class);
    }

    // CREATE
    public StudentBadgeDTO assignBadgeToStudent(Long studentId, Long badgeId) {
        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found"));

        StudentBadge studentBadge = new StudentBadge(studentId, badge, LocalDateTime.now());
        StudentBadge savedBadge = studentBadgeRepository.save(studentBadge);

        return convertToDTO(savedBadge);
    }

    // Get All Badges for a Student
    public List<StudentBadgeDTO> getBadgesByStudentId(Long studentId) {
        List<StudentBadge> badges = studentBadgeRepository.findByStudentId(studentId);
        return badges
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // DELETE
    @Transactional
    public void deleteStudentBadge(Long studentId, Long badgeId) {
        studentBadgeRepository.deleteByStudentIdAndBadgeId(studentId, badgeId);
    }
}
