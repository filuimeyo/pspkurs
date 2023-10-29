package com.example.springboot.rating;


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
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;


    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @JsonBackReference
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    @JsonBackReference
    private Student student;


    public Rating(Integer rating, Teacher teacher, Student student) {
        this.rating = rating;
        this.teacher = teacher;
        this.student = student;
    }
}
