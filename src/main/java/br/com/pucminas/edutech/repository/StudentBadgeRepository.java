package br.com.pucminas.edutech.repository;

import br.com.pucminas.edutech.model.entity.StudentBadge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentBadgeRepository extends JpaRepository<StudentBadge, Long> {
    List<StudentBadge> findByStudentId(Long studentId);

    @Transactional
    void deleteByStudentIdAndBadgeId(Long studentId, Long badgeId);
}
