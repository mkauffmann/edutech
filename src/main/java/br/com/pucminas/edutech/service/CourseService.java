package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.BadgeDTO;
import br.com.pucminas.edutech.model.dto.CourseDTO;
import br.com.pucminas.edutech.model.entity.Badge;
import br.com.pucminas.edutech.model.entity.Course;
import br.com.pucminas.edutech.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private CourseDTO convertToDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }

    // DTO to Entity
    private Course convertToEntity(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

}
