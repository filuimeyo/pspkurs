package com.example.springboot.student;

import com.example.springboot.appuser.AppUser;
import com.example.springboot.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s.likedTeachers from Student s where s.id = ?1")
    public List<Teacher> getLikedTeachers(long studentId);

    @Query("SELECT u from Student s inner join s.appUser u where s.id = ?1")
    Optional<AppUser> getStudentAndAppUserById(Long id);
}


