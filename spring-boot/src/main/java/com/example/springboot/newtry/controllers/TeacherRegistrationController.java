package com.example.springboot.newtry.controllers;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.models.Teacher;
import com.example.springboot.newtry.services.StudentRegistrationService;
import com.example.springboot.newtry.services.TeacherRegistrationService;
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
@RequestMapping(path = "api/v1/registration/teacher")
@AllArgsConstructor
public class TeacherRegistrationController {
    private final TeacherRegistrationService teacherService;

    @GetMapping
    public List<Teacher> getStudents() {
        return teacherService.getTeachers();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Teacher teacher) {
        teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) Double price){
        teacherService.updateTeacher(studentId, firstName, lastName, price);
    }

}
