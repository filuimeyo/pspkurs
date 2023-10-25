package com.example.springboot.view;

import com.example.springboot.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTeacherViewService {
    private final SubjectTeacherCountRepository countRepository;


    public SubjectTeacherViewService(SubjectTeacherCountRepository countRepository) {
        this.countRepository = countRepository;
    }

    public List<SubjectTeacherCount> getAll(){
       /* List<Subject> list =  subjectRepository.findAll();
        List<V>
        for ( Subject s: list) {
            s.setCount();
        }*/

        //System.out.println(service.getViews());
        return  countRepository.findAll();

    }
}
