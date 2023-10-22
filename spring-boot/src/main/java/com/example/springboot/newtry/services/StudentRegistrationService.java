package com.example.springboot.newtry.services;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.repositories.MyStudentRepository;
import com.example.springboot.student.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentRegistrationService {
    private final MyStudentRepository myStudentRepository;


    public StudentRegistrationService(MyStudentRepository myStudentRepository) {
        this.myStudentRepository = myStudentRepository;
    }

    public List<MyStudent> getStudents(){
        return myStudentRepository.findAll();
    }

    public void addNewStudent(MyStudent myStudent) {
        myStudentRepository.save(myStudent);
    }

    public void deleteStudent(Long id) {
        boolean exist = myStudentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException(
                    "student with id " + id + " does not exist"
            );
        }
        myStudentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName) {
        MyStudent student = myStudentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        if(firstName!=null && !firstName.isEmpty() && Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }
        if(lastName!=null && !lastName.isEmpty() && Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }

    }

}
