package com.example.springboot.newtry.services;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.models.Teacher;
import com.example.springboot.newtry.repositories.MyStudentRepository;
import com.example.springboot.newtry.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherRegistrationService {
    private final TeacherRepository teacherRepository;

    public TeacherRegistrationService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        boolean exist = teacherRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException(
                    "teacher with id " + id + " does not exist"
            );
        }
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long studentId, String firstName, String lastName, Double price) {
        Teacher teacher = teacherRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        if(firstName!=null && !firstName.isEmpty() && Objects.equals(teacher.getFirstName(), firstName)){
            teacher.setFirstName(firstName);
        }
        if(lastName!=null && !lastName.isEmpty() && Objects.equals(teacher.getLastName(), lastName)){
            teacher.setLastName(lastName);
        }
        if(price!=null && !price.isNaN() && Objects.equals(teacher.getLessonPrice(), price)){
            teacher.setLessonPrice(price);
        }
    }
}
