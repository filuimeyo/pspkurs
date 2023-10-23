package com.example.springboot.mystudent;

import com.example.springboot.mystudent.MyStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStudentRepository extends JpaRepository<MyStudent, Long> {

}
