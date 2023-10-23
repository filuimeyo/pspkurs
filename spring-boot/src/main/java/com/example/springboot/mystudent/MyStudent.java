package com.example.springboot.mystudent;

import com.example.springboot.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MyStudent {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "user_id")
    private AppUser appUser;


    public MyStudent(String firstName, String lastName, AppUser appUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.appUser = appUser;
    }
}
