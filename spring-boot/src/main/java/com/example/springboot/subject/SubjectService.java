package com.example.springboot.subject;

import com.example.springboot.teacher.Teacher;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    private final String FOLDER_PATH = "D:/Desktop/uploadspspkurs/subject/";


    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }




    public List<Object[]> getAll(String name) {
        return subjectRepository.getCountOfTeachersBySubject(name);
    }

    public List<Subject> getMostPopularSubjects(PageRequest pageRequest) {
        return subjectRepository.getMostPopularSubjects(pageRequest);
    }

    public List<Teacher> getTeachersBySubjectId(long subjectId){
        return subjectRepository.findTeachersBySubjectId(subjectId);
    }


    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public String addNewSubject(String subjectName, MultipartFile file) throws IOException {

        boolean subjectIsPresent = subjectRepository.findSubjectByName(subjectName).isPresent();


        if(subjectIsPresent){
            throw new  IllegalStateException("name already taken");
        }

        Subject subjectToSave = new Subject();
        subjectToSave.setName(subjectName);
        Subject savedData;

        if(file != null){
            String filename = UUID.randomUUID().toString() + ".jpg";
            Path saveTO = Paths.get(FOLDER_PATH + filename);

            subjectToSave.setFileName(filename);
            subjectToSave.setFilePath(String.valueOf(saveTO));
            savedData =  subjectRepository.save(subjectToSave);
            Files.copy(file.getInputStream(), saveTO);
        } else savedData =  subjectRepository.save(subjectToSave);

        if(savedData!=null) return "subject added successfully";
        return null;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Subject> im = subjectRepository.findSubjectByFileName(fileName);

        System.out.println(im.get());
        byte[] images = Files.readAllBytes(
                new File( FOLDER_PATH + im.get().getFileName()).toPath());

        return images;
    }

}
