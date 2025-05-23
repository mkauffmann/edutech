package br.com.pucminas.edutech.repository;

import br.com.pucminas.edutech.model.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, String> {
    List<StudentCourse> findByStudentId(String studentId);

}
