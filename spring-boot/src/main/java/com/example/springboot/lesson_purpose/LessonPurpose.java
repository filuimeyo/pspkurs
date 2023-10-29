package com.example.springboot.lesson_purpose;

import com.example.springboot.lesson_application.LessonApplication;
import com.example.springboot.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LessonPurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String purpose;


    @ManyToMany(mappedBy = "purposes")
    @JsonBackReference
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy="purpose")
    @JsonManagedReference
    private Set<LessonApplication> lessonApplications;


}
