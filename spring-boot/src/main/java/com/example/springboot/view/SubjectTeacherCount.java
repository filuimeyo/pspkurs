package com.example.springboot.view;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
//@Table(name="subject_teacher_count")
@Immutable
public class SubjectTeacherCount {

    @Id
   // @Column(name="subject_id")
    private Long subjectId;

   // @Column(name="count")
    private Long count;

}
