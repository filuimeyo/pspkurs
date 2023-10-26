package com.example.springboot.subject;

import com.example.springboot.view.SubjectTeacherCount;
import com.example.springboot.view.SubjectTeacherViewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

   /* @Value(value = "${apload.path}")
    private String uploadpath;*/

    private final SubjectRepository subjectRepository;
    private final SubjectTeacherViewService subjectTeacherViewService;

    public SubjectService(SubjectRepository subjectRepository, SubjectTeacherViewService subjectTeacherViewService) {
        this.subjectRepository = subjectRepository;

        this.subjectTeacherViewService = subjectTeacherViewService;
    }

    public Optional<List<Subject>> get(){
        return subjectRepository.findWithCount();
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();

    }


    public List<Subject> getSubjectsWithTeacherCount() {
        List<Subject> list1 = subjectRepository.findAll();
        List<SubjectTeacherCount> list2 = subjectTeacherViewService.getAll();

        for (int i = 0; i < list1.size(); i++) {
            list1.get(i).setCount(list2.get(i).getCount());
        }

        return list1;
    }


    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public void addNewSubject(String subjectName, MultipartFile file) throws IOException {


    }
}
