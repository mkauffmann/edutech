package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.LessonDTO;
import br.com.pucminas.edutech.model.entity.Lesson;
import br.com.pucminas.edutech.repository.LessonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    public LessonService(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private LessonDTO convertToDTO(Lesson lesson) {
        return modelMapper.map(lesson, LessonDTO.class);
    }

    // DTO to Entity
    private Lesson convertToEntity(LessonDTO lessonDTO) {
        return modelMapper.map(lessonDTO, Lesson.class);
    }

    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = convertToEntity(lessonDTO);
        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDTO(savedLesson);
    }

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public LessonDTO getLessonById(Long id){
        Lesson badge = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No lesson with id " + id + " found"));
        return convertToDTO(badge);
    }

    public LessonDTO updateLesson(Long id, LessonDTO lessonDTO){
        Lesson existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No lesson with id " + id + " found"));

        existingLesson.setName(lessonDTO.getName());
        existingLesson.setDescription(lessonDTO.getDescription());
        existingLesson.setVideoUrl(lessonDTO.getVideoUrl());

        Lesson updatedLesson = lessonRepository.save(existingLesson);

        return convertToDTO(updatedLesson);
    }

    public void deleteLesson(Long id){
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No lesson with id " + id + " found"));

        lessonRepository.delete(lesson);
    }


}
