package com.example.springboot.student;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException(
                    "student with id " + id + " does not exist"
            );
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName) {
        Student student = studentRepository.findById(studentId)
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
