package com.example.springboot.teacher;

import com.example.springboot.student.Student;
import com.example.springboot.subject.Subject;
import com.example.springboot.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT teacher.*" +
            " from teacher" +
            " inner join teacher_subject" +
            " on teacher.id = teacher_subject.teacher_id\n" +
            " where subject_id = ?1",
    nativeQuery = true)
    Optional<List<Teacher>> findTeachersBySubjectId(Long subject_id);


    @Query("SELECT t FROM Teacher t WHERE t.fileName = ?1")
    Optional<Teacher> findTeacherByFileName(String fileName);

}


