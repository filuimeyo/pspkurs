package com.example.springboot.teacher;

import com.example.springboot.appuser.AppUser;
import com.example.springboot.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher implements java.io.Serializable {
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private double lessonPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "user_id")
    private AppUser appUser;



    @ManyToMany()
    @JoinTable(
            name = "teacher_subject",
            joinColumns =  @JoinColumn(name = "teacher_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    //@JsonBackReference
    private Set<Subject> teacherSubjects = new HashSet<>();



    public Teacher(String firstName, String lastName, double lessonPrice, AppUser appUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonPrice = lessonPrice;
        this.appUser = appUser;
    }

    public Teacher(String firstName, String lastName, double lessonPrice, AppUser appUser, Set<Subject> teacherSubjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonPrice = lessonPrice;
        this.appUser = appUser;
        this.teacherSubjects = teacherSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Double.compare(lessonPrice, teacher.lessonPrice) == 0 && Objects.equals(id, teacher.id) && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(appUser, teacher.appUser) && Objects.equals(teacherSubjects, teacher.teacherSubjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, lessonPrice, appUser, teacherSubjects);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lessonPrice=" + lessonPrice +
                ", appUser=" + appUser +
                ", teacherSubjects=" + teacherSubjects +
                '}';
    }
}
