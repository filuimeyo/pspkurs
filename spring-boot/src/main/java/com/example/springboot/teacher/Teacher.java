package com.example.springboot.teacher;

import com.example.springboot.lesson_application.LessonApplication;
import com.example.springboot.lesson_purpose.LessonPurpose;
import com.example.springboot.rating.Rating;
import com.example.springboot.appuser.AppUser;
import com.example.springboot.certificate.Certificate;
import com.example.springboot.student.Student;
import com.example.springboot.subject.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher {
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

    @Column(columnDefinition = "TEXT")
    private String info;
    private double lessonPrice;

    private  String fileName;
    private String filePath;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "user_id")
    private AppUser appUser;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_subject",
            joinColumns =  @JoinColumn(name = "teacher_id") ,
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @JsonManagedReference
    private Set<Subject> teacherSubjects = new HashSet<>();



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_purpose",
            joinColumns =  @JoinColumn(name = "teacher_id") ,
            inverseJoinColumns = @JoinColumn(name = "purpose_id")
    )
    @JsonManagedReference
    private Set<LessonPurpose> purposes = new HashSet<>();


    @ManyToMany(mappedBy = "likedTeachers")
    @JsonBackReference
    private List<Student> students ;


    @OneToMany(mappedBy="teacher")
    @JsonManagedReference
    private Set<Certificate> certificates;


    @OneToMany(mappedBy="teacher")
    @JsonManagedReference
    private Set<Rating> teacherRating;

    @Transient
    private Double finalRating;


    @OneToMany(mappedBy="teacher")
    @JsonManagedReference
    private Set<LessonApplication> lessonApplications;

    public Teacher(String firstName, String lastName, double lessonPrice, AppUser appUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lessonPrice = lessonPrice;
        this.appUser = appUser;
    }

    public Teacher(String firstName, double lessonPrice, AppUser appUser) {
        this.firstName = firstName;
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

    public void setFinalRating() {
        this.finalRating = (double) teacherRating.size();
                /*.stream().map(Rating::getRating).map(Double::valueOf)
                .reduce(Double::sum).map(it->it/teacherRating.size())
                .orElse(0.0);*/
    }


    public Double getFinalRating() {
        return  teacherRating.stream().map(Rating::getRating).map(Double::valueOf)
                .reduce(Double::sum).map(it->it/teacherRating.size())
                .orElse(0.0);
    }
}
