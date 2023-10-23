package com.example.springboot.mystudent;

import com.example.springboot.mystudent.MyStudent;
import com.example.springboot.mystudent.MyStudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class MyStudentService {
    private final MyStudentRepository myStudentRepository;


    public MyStudentService(MyStudentRepository myStudentRepository) {
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
