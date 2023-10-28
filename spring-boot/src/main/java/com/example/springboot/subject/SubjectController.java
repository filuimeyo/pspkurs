package com.example.springboot.subject;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> addSubject(
            @RequestParam String subject,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String result =  subjectService.addNewSubject(subject, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping(path = "image/{fileName}")
    public ResponseEntity<?> uploadImage(
            @PathVariable String fileName
    ) throws IOException {
        System.out.println(fileName);
        byte[] im = subjectService.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);

    }

    @GetMapping(path = "all")
    public List<Object[]> getALL(){
        return subjectService.getAll();
    }


    @GetMapping
    public List<Subject> getSubjects(){
       return subjectService.getSubjects();
    }

    @GetMapping(path = "{id}")
    public Optional<Subject> getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }



}
