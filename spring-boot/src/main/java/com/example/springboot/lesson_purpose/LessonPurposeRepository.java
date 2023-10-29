package com.example.springboot.lesson_purpose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface LessonPurposeRepository extends JpaRepository<LessonPurpose, Long> {

    @Query("SELECT l FROM LessonPurpose l where l.purpose = ?1")
    Optional<LessonPurpose> findByPurpose(String purpose);
}
