package com.example.springboot.subject;

import com.example.springboot.student.Student;
import com.example.springboot.subject.Subject;
import com.example.springboot.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {


    //todo: filter enabled and locked users
    @Query("SELECT s, COALESCE(COUNT(t), 0)  FROM Subject s " +
            "left JOIN s.teachers t " +
            " group by s")
    List<Object[]> getCountOfTeachersBySubject(); //для главной и для др

    @Query("SELECT s FROM Subject s WHERE s.name = ?1")
    Optional<Subject> findSubjectByName(String name);

    @Query("SELECT s FROM Subject s WHERE s.fileName = ?1")
    Optional<Subject> findSubjectByFileName(String fileName);


    @Query("select s.teachers from Subject s where s.id = ?1")
    List<Teacher> findTeachersBySubjectId(long subjectId);



}



