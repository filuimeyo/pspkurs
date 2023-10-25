package com.example.springboot.subject;

import com.example.springboot.view.SubjectTeacherCount;
import com.example.springboot.view.SubjectTeacherViewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectTeacherViewService subjectTeacherViewService;

    public SubjectService(SubjectRepository subjectRepository, SubjectTeacherViewService subjectTeacherViewService) {
        this.subjectRepository = subjectRepository;

        this.subjectTeacherViewService = subjectTeacherViewService;
    }

    public List<Subject> getSubjects() {
       /* List<Subject> list =  subjectRepository.findAll();
        List<V>
        for ( Subject s: list) {
            s.setCount();
        }*/


        //System.out.println(service.getViews());
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
}
