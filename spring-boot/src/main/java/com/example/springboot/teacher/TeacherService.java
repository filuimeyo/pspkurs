package com.example.springboot.teacher;

import com.example.springboot.subject.SubjectRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        boolean exist = teacherRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException(
                    "teacher with id " + id + " does not exist"
            );
        }
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long studentId, String firstName, String lastName, Double price) {
        Teacher teacher = teacherRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        if (firstName != null && !firstName.isEmpty() && Objects.equals(teacher.getFirstName(), firstName)) {
            teacher.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty() && Objects.equals(teacher.getLastName(), lastName)) {
            teacher.setLastName(lastName);
        }
        if (price != null && !price.isNaN() && Objects.equals(teacher.getLessonPrice(), price)) {
            teacher.setLessonPrice(price);
        }
    }


}
