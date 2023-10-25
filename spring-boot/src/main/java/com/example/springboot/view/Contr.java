package com.example.springboot.view;

import com.example.springboot.subject.Subject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration/count")
@AllArgsConstructor
public class Contr {

    private final SubjectTeacherViewService service;
    @GetMapping
    public List<SubjectTeacherCount> getCounts(){
        return service.getAll();
    }

}
