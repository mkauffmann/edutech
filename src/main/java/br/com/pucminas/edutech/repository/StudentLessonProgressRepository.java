package br.com.pucminas.edutech.repository;

import br.com.pucminas.edutech.model.entity.StudentLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentLessonProgressRepository extends JpaRepository<StudentLessonProgress, Long> {
    List<StudentLessonProgress> findByStudentId(Long studentId);

    Optional<StudentLessonProgress> findByStudentIdAndLessonId(Long studentId, Long lessonId);


}
