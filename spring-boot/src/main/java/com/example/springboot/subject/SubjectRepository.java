package com.example.springboot.subject;

import com.example.springboot.student.Student;
import com.example.springboot.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {


    @Query(value = "SELECT subject.*, count from subject " +
            "inner join public.subject_teacher_count stc on subject.id = stc.subject_id"
            , nativeQuery = true)
    Optional<List<Subject>> findWithCount();


    @Query("SELECT s, COALESCE(COUNT(t), 0)  FROM Subject s LEFT JOIN s.teachers t GROUP BY s")
    List<Object[]> getCountOfTeachersBySubject();


    @Query("SELECT s FROM Subject s WHERE s.name = ?1")
    Optional<Subject> findSubjectByName(String name);
}
