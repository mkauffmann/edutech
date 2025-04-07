package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.model.dto.LessonDTO;
import br.com.pucminas.edutech.model.entity.Lesson;
import br.com.pucminas.edutech.model.entity.Course;
import br.com.pucminas.edutech.repository.LessonRepository;
import br.com.pucminas.edutech.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;


    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCoverImgUrl(course.getCoverImgUrl());
        dto.setLessons(course.getLessons().stream().map(lesson -> modelMapper.map(lesson, LessonDTO.class)).toList());

        return dto;
    }

    // DTO to Entity
    private Course convertToEntity(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        course.setLessons(new ArrayList<Lesson>());
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CourseDTO getCourseById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No course with id " + id + " found"));
        return convertToDTO(course);
    }

    public CourseDTO updateCourseInfo(Long id, CourseDTO courseDTO){
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No course with id " + id + " found"));

        existingCourse.setName(courseDTO.getName());
        existingCourse.setDescription(courseDTO.getDescription());
        existingCourse.setCoverImgUrl(courseDTO.getCoverImgUrl());
        existingCourse.setLessons(existingCourse.getLessons());

        Course updatedCourse = courseRepository.save(existingCourse);

        return convertToDTO(updatedCourse);
    }

    public CourseDTO addLessonToCourse(Long courseId, Long classId){
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("No course with id " + courseId + " found"));

        Lesson existingLesson = lessonRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("No class with id " + classId + " found"));

        List<Lesson> currentLessons = existingCourse.getLessons();
        currentLessons.add(existingLesson);

        existingCourse.setLessons(currentLessons);

        Course updatedCourse = courseRepository.save(existingCourse);

        return convertToDTO(updatedCourse);
    }

    public CourseDTO deleteLessonFromCourse(Long courseId, Long lessonId){
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("No course with id " + courseId + " found"));

        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("No lesson with id " + lessonId + " found"));

        List<Lesson> currentLessons = existingCourse.getLessons();

        if (!currentLessons.contains(existingLesson)){
            throw new EntityNotFoundException("No lesson with id " + lessonId + " found in this course");
        }

        currentLessons.remove(existingLesson);
        existingCourse.setLessons(currentLessons);

        Course updatedCourse = courseRepository.save(existingCourse);

        return convertToDTO(updatedCourse);

    }

    public void deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No course with id " + id + " found"));

        courseRepository.delete(course);
    }

}
