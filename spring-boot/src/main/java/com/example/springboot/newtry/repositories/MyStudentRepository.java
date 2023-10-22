package com.example.springboot.newtry.repositories;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MyStudentRepository extends JpaRepository<MyStudent, Long> {

}
