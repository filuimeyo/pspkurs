package com.example.springboot.teacher;

import com.example.springboot.certificate.Certificate;
import com.example.springboot.certificate.CertificateRepository;
import com.example.springboot.subject.Subject;
import com.example.springboot.subject.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TeacherService {
    private final String FOLDER_PATH = "D:/Desktop/uploadspspkurs/";
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final CertificateRepository certificateRepository;


    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, SubjectRepository subjectRepository1, CertificateRepository certificateRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository1;
        this.certificateRepository = certificateRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }


    public Teacher assignSubjectToTeacher(Long teacherId, Long subjectId) {

        Set<Subject> subjectSet = null;
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException(
                        "teacher with id " + teacherId + " does not exist"
                ));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException(
                        "subject with id " + subjectId + " does not exist"
                ));
        subjectSet = teacher.getTeacherSubjects();
        subjectSet.add(subject);
        teacher.setTeacherSubjects(subjectSet);
        return teacherRepository.save(teacher);
    }


    public Teacher deleteSubjectFromTeacher(Long teacherId, Long subjectId) {
        Set<Subject> subjectSet = null;
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException(
                        "teacher with id " + teacherId + " does not exist"
                ));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException(
                        "subject with id " + subjectId + " does not exist"
                ));
        subjectSet = teacher.getTeacherSubjects();
        subjectSet.remove(subject);
        teacher.setTeacherSubjects(subjectSet);
        return teacherRepository.save(teacher);
    }

    public Optional<List<Teacher>> findTeachersBySubjectId(Long subjectId) {
        return teacherRepository.findTeachersBySubjectId(subjectId);
    }


    public void deleteTeacher(Long id) {
        boolean exist = teacherRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException(
                    "teacher with id " + id + " does not exist"
            );
        }
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long studentId, String firstName, String lastName, Double price) {
        Teacher teacher = teacherRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        if (firstName != null && !firstName.isEmpty() && Objects.equals(teacher.getFirstName(), firstName)) {
            teacher.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty() && Objects.equals(teacher.getLastName(), lastName)) {
            teacher.setLastName(lastName);
        }
        if (price != null && !price.isNaN() && Objects.equals(teacher.getLessonPrice(), price)) {
            teacher.setLessonPrice(price);
        }
    }

    public String addNewCertificate(Long teacherId, MultipartFile file) throws IOException {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException(
                        "teacher with id " + teacherId + " does not exist"
                        ));


        String filename = UUID.randomUUID() + ".jpg";
        String saveTO = FOLDER_PATH + filename;

        Certificate certificateData = certificateRepository.save(
                new Certificate(filename, saveTO, teacher)
        );

        Files.copy(file.getInputStream(), Path.of(saveTO));
        if (certificateData != null) {
            return "file uploaded successfully: " + saveTO;
        }
        return null;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Certificate> im = certificateRepository.findCertificateByFileName(fileName);

        System.out.println(im.get());
        byte[] images = Files.readAllBytes(
                new File(FOLDER_PATH + im.get().getFileName()).toPath());

        return images;
    }

}
