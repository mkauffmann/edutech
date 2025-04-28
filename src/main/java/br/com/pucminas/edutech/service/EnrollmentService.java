package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.model.entity.Course;
import br.com.pucminas.edutech.model.entity.StudentCourse;
import br.com.pucminas.edutech.repository.CourseRepository;
import br.com.pucminas.edutech.repository.StudentCourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public EnrollmentService(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));

        StudentCourse enrollment = new StudentCourse();
        enrollment.setStudentId(studentId);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        studentCourseRepository.save(enrollment);
    }

    public List<CourseDTO> getCoursesForStudent(Long studentId) {
        List<StudentCourse> enrollments = studentCourseRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment.getCourse(), CourseDTO.class))
                .toList();
    }

}
