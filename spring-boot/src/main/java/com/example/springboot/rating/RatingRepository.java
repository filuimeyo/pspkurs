package com.example.springboot.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.student.id = ?1 AND r.teacher.id = ?2")
    Optional<Rating> findRatingByStudentAndTeacher(Long studentId, Long teacherId);
}
