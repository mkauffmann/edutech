package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.ClassDTO;
import br.com.pucminas.edutech.model.entity.Class;
import br.com.pucminas.edutech.repository.ClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;

    public ClassService(ClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private ClassDTO convertToDTO(Class clazz) {
        return modelMapper.map(clazz, ClassDTO.class);
    }

    // DTO to Entity
    private Class convertToEntity(ClassDTO classDTO) {
        return modelMapper.map(classDTO, Class.class);
    }


}
