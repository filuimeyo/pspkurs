package com.example.springboot.newtry.controllers;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.services.StudentRegistrationService;
import com.example.springboot.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration/student")
@AllArgsConstructor
public class StudentRegistrationController {
    private final StudentRegistrationService myStudentService;

    @GetMapping
    public List<MyStudent> getStudents() {
        return myStudentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody MyStudent student) {
        myStudentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long studentId) {
        myStudentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName){
        myStudentService.updateStudent(studentId, firstName, lastName);
    }

}
