package com.example.springboot.newtry.controllers;

import com.example.springboot.newtry.models.MyStudent;
import com.example.springboot.newtry.models.Subject;
import com.example.springboot.newtry.repositories.SubjectRepository;
import com.example.springboot.newtry.services.StudentRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration/subject")
@AllArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepository;


    @GetMapping
    public List<Subject> getStudents(){
       return subjectRepository.findAll();
    }



}
