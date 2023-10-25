package com.example.springboot.teacher;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration/teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

   /* @DeleteMapping(path = "{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }*/

    //findTeachersBySubjectId

    @GetMapping(path = "{subject_id}")
    public Optional<List<Teacher>> getTeachers(@PathVariable("subject_id") Long id) {
        return teacherService.findTeachersBySubjectId(id);
    }

    @GetMapping(path = "one/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.findTeacherById(id);
    }


    @PutMapping(path = "{id}")
    public void updateTeacher(@PathVariable("id") Long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) Double price){
        teacherService.updateTeacher(id, firstName, lastName, price);
    }

}
