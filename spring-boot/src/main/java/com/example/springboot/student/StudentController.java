package com.example.springboot.student;

import com.example.springboot.teacher.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path ="{id}")
    public Optional<Student> getStudentById(
            @PathVariable Long id
    ) {
        return studentService.getStudentById(id);
    }


    @PostMapping(path="apply/subject/{subjectId}/{studentId}")
    public String applyForSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ){
        return null;
    }

    @PostMapping(path="apply/teacher/{subjectId}/{studentId}")
    public String applyForTeacher(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ){
        return null;
    }

    @GetMapping(path = "liked/{studentId}")
    public List<Teacher> getLikedTeachers(
            @PathVariable Long studentId
    ) {
        return studentService.getLikedTeachers(studentId);
    }


    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        studentService.updateStudent(studentId, firstName, lastName);
    }

}
