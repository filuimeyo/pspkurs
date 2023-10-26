package com.example.springboot.subject;

import com.example.springboot.student.Student;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;



    @PostMapping
    public void addSubject(
            @RequestParam String subject,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
       subjectService.addNewSubject(subject, file);
    }


    @GetMapping
    public List<Subject> getSubjects(){
       return subjectService.getSubjects();
    }

    @GetMapping(path = "all")
    public List<Subject> getSubjectsWithCount(){
        return subjectService.getSubjectsWithTeacherCount();
    }

    @GetMapping(path = "all2")
    public List<Subject> getS(){
        return subjectService.getSubjects();
    }


    //getSubjects

    @GetMapping(path = "{id}")
    public Optional<Subject> getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }



}
