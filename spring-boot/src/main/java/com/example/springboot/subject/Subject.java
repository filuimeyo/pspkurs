package com.example.springboot.subject;


import com.example.springboot.lesson_application.LessonApplication;
import com.example.springboot.rating.Rating;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subject {

    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    private Long id;
    private String name;

    private  String fileName;
    private String filePath;


    @ManyToMany(mappedBy = "teacherSubjects")
    @JsonBackReference
    private Set<Teacher> teachers = new HashSet<>();


    @OneToMany(mappedBy="subject")
    @JsonManagedReference
    private Set<LessonApplication> lessonApplications;


    public Subject(String name) {
        this.name = name;
    }

    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(String name, String fileName, String filePath) {
        this.name = name;
        this.fileName = fileName;
        this.filePath = filePath;
    }

}
