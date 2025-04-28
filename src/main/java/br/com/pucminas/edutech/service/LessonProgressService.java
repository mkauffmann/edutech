package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.LessonDTO;
import br.com.pucminas.edutech.model.entity.Lesson;
import br.com.pucminas.edutech.model.entity.StudentLessonProgress;
import br.com.pucminas.edutech.repository.LessonRepository;
import br.com.pucminas.edutech.repository.StudentLessonProgressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonProgressService {
    private final StudentLessonProgressRepository studentLessonProgressRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    public LessonProgressService(StudentLessonProgressRepository studentLessonProgressRepository, LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.studentLessonProgressRepository = studentLessonProgressRepository;
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    public void markLessonAsWatched(Long studentId, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id " + lessonId));

        StudentLessonProgress progress = new StudentLessonProgress();
        progress.setStudentId(studentId);
        progress.setLesson(lesson);
        progress.setWatchedAt(LocalDateTime.now());

        studentLessonProgressRepository.save(progress);
    }

    public List<LessonDTO> getLessonsWatchedByStudent(Long studentId) {
        List<StudentLessonProgress> progresses = studentLessonProgressRepository.findByStudentId(studentId);
        return progresses.stream()
                .map(progress -> modelMapper.map(progress.getLesson(), LessonDTO.class))
                .toList();
    }

}
