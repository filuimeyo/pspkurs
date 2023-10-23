package com.example.springboot.subject;

import com.example.springboot.subject.Subject;
import com.example.springboot.subject.SubjectRepository;
import com.example.springboot.teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;

    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
}
