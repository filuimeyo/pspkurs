package com.example.springboot.feedback_on_teacher;

import com.example.springboot.student.Student;
import com.example.springboot.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class feedbackOnTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedbackText;


    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @JsonBackReference
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    @JsonBackReference
    private Student student;


    public feedbackOnTeacher(String feedbackText, Teacher teacher, Student student) {
        this.feedbackText = feedbackText;
        this.teacher = teacher;
        this.student = student;
    }
}

