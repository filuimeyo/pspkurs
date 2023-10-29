package com.example.springboot.teacher;

import com.example.springboot.certificate.Certificate;
import com.example.springboot.certificate.CertificateService;
import com.example.springboot.rating.Rating;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(path = "subject/{subject_id}")
    public Optional<List<Teacher>> getTeachers(@PathVariable("subject_id") Long id) {
        return teacherService.findTeachersBySubjectId(id);
    }

    @GetMapping(path = "one/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.findTeacherById(id);
    }


    @GetMapping(path = "imageofdocs/{fileName}")
    public ResponseEntity<?> uploadImage(
            @PathVariable String fileName
    ) throws IOException {
        System.out.println(fileName);
        byte[] im = teacherService.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(im);

    }


    @PostMapping(path = "imageofdocs/{teacherId}")
    public String addCertificate(
            @PathVariable Long teacherId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return teacherService.addNewCertificate(teacherId, file);
    }


    @PutMapping(path="rating/{studentId}/{teacherId}/{rating}")
    public String addRating(
            @PathVariable Long studentId,
            @PathVariable Long teacherId,
            @PathVariable Integer rating
    ){
        return teacherService.addRating(studentId, teacherId, rating);
    }


    @PutMapping("assign/{teacherId}/{subjectId}")
    public Teacher assignSubjectToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId
    ){
        return teacherService.assignSubjectToTeacher(teacherId, subjectId);
    }

    @DeleteMapping("assign/{teacherId}/{subjectId}")
    public Teacher deleteSubjectFromTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId
    ){
        return teacherService.deleteSubjectFromTeacher(teacherId, subjectId);
    }




    @PutMapping(path = "{id}")
    public void updateTeacher(@PathVariable("id") Long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) Double price){
        teacherService.updateTeacher(id, firstName, lastName, price);
    }

}
