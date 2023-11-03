package com.example.springboot.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.student.id = ?1 AND r.teacher.id = ?2")
    Optional<Rating> findRatingByStudentAndTeacher(Long studentId, Long teacherId);

    @Query("select r, s from Rating r inner join r.student s where r.teacher.id = ?1")
    List<Object> getByTeacherId(Long teacherId);

    @Query("select coalesce(avg(r.rating), 0) from Rating r  where r.teacher.id = ?1")
    Double getFinalRatingByTeacherId(Long teacherId);
}
