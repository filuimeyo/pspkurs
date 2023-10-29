package com.example.springboot.lesson_application;

import com.example.springboot.lesson_purpose.LessonPurpose;
import com.example.springboot.student.Student;
import com.example.springboot.subject.Subject;
import com.example.springboot.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private String details;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
    @JsonBackReference
    private Subject subject;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @JsonBackReference
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name="purpose_id", nullable=false)
    @JsonBackReference
    private LessonPurpose purpose;


}
