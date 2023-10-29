package com.example.springboot.student;

import com.example.springboot.lesson_application.LessonApplication;
import com.example.springboot.rating.Rating;
import com.example.springboot.appuser.AppUser;
import com.example.springboot.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @SequenceGenerator(
            name = "my_student_sequence",
            sequenceName = "my_student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_student_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns =  @JoinColumn(name = "my_student_id") ,
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnore
    private Set<Teacher> likedTeachers;


    @OneToMany(mappedBy="student")
    @JsonIgnore
    private Set<Rating> teacherRating;

    @OneToMany(mappedBy="student")
    @JsonManagedReference
    private Set<LessonApplication> lessonApplications;

    public Student(String firstName, String lastName, AppUser appUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.appUser = appUser;
    }

    public Student(String firstName, String lastName, AppUser appUser, Set<Teacher> likedTeachers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.appUser = appUser;
        this.likedTeachers = likedTeachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(appUser, student.appUser) && Objects.equals(likedTeachers, student.likedTeachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, appUser, likedTeachers);
    }

    @Override
    public String toString() {
        return "MyStudent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", appUser=" + appUser +
                ", likedTeachers=" + likedTeachers +
                '}';
    }
}
