package br.com.pucminas.edutech.repository;

import br.com.pucminas.edutech.model.entity.StudentPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPointsRepository extends JpaRepository<StudentPoints, String> {
    public List<StudentPoints> findAllByOrderByPointsDesc();
}
