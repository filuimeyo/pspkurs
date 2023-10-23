package com.example.springboot.subject;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;



    @GetMapping
    public List<Subject> getSubjects(){
       return subjectService.getSubjects();
    }


    @GetMapping(path = "{id}")
    public Optional<Subject> getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }



}
