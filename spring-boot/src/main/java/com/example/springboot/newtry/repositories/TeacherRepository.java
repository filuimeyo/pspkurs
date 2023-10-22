package com.example.springboot.newtry.repositories;

import com.example.springboot.newtry.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
