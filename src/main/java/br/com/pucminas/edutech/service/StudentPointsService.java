package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.StudentPointsDTO;
import br.com.pucminas.edutech.model.entity.StudentPoints;
import br.com.pucminas.edutech.repository.StudentPointsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentPointsService {
    private final StudentPointsRepository studentPointsRepository;
    private final ModelMapper modelMapper;

    public StudentPointsService(StudentPointsRepository studentPointsRepository, ModelMapper modelMapper) {
        this.studentPointsRepository = studentPointsRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private StudentPointsDTO convertToDTO(StudentPoints studentPoints) {
        return modelMapper.map(studentPoints, StudentPointsDTO.class);
    }

    // DTO to Entity
    private StudentPoints convertToEntity(StudentPointsDTO studentPointsDTO) {
        return modelMapper.map(studentPointsDTO, StudentPoints.class);
    }

    public StudentPointsDTO updatePoints(Long studentId, int points){
        StudentPoints currentStudentPoints = studentPointsRepository.findById(studentId)
                .orElseGet(() -> new StudentPoints(studentId, LocalDateTime.now())); //In case no student is found, create new studentPoints entry with 0 points

        currentStudentPoints.setPoints(currentStudentPoints.getPoints() + points);
        currentStudentPoints.setUpdatedAt(LocalDateTime.now());

        StudentPoints updatedPoints = studentPointsRepository.save(currentStudentPoints);

        return convertToDTO(updatedPoints);
    }

    public List<StudentPointsDTO> getStudentPointsRanking(){
        return studentPointsRepository.findAllByOrderByPointsDesc()
                .stream()
                .map(this::convertToDTO)
                .toList();

    }

}
