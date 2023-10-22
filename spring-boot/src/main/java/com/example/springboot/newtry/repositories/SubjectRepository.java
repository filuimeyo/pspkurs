package com.example.springboot.newtry.repositories;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
