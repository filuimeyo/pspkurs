package com.example.springboot.teacher;

import com.example.springboot.certificate.Certificate;
import com.example.springboot.certificate.CertificateRepository;
import com.example.springboot.rating.Rating;
import com.example.springboot.rating.RatingRepository;
import com.example.springboot.student.Student;
import com.example.springboot.student.StudentRepository;
import com.example.springboot.subject.Subject;
import com.example.springboot.subject.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TeacherService {
    private final String FOLDER_PATH = "D:/Desktop/uploadspspkurs/";
    private final String PROFILE_PICTURES_FOLDER_PATH = "D:/Desktop/uploadspspkurs/teacherpic/";
    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final CertificateRepository certificateRepository;

    private final RatingRepository ratingRepository;


    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, SubjectRepository subjectRepository1, CertificateRepository certificateRepository, RatingRepository ratingRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository1;
        this.certificateRepository = certificateRepository;
        this.ratingRepository = ratingRepository;
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


    public String addRating(Long studentId, Long teacherId, Integer rating){

        if(rating==null || rating >10 || rating <1){
            throw new IllegalStateException(
                    "cant assign such value, it should be between 1 and 10"
            );
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException(
                        "teacher with id " + studentId + " does not exist"
                ));


        Optional<Rating> ratingData =  ratingRepository.findRatingByStudentAndTeacher(studentId, teacherId);


        if(ratingData.isPresent()){
            ratingData.get().setRating(rating);
            ratingRepository.save(ratingData.get());

            return "Rating updated successfully";

        } else{
             ratingRepository.save(
                    new Rating(rating, teacher, student)
            );

            return "Rating added successfully";
        }

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
                        "teacher with id " + studentId + " does not exist"
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

    public byte[] downloadTeacherImageFromFileSystem(String fileName) throws IOException {

        Optional<Teacher> im = teacherRepository.findTeacherByFileName(fileName);


        byte[] images = Files.readAllBytes(
                new File(PROFILE_PICTURES_FOLDER_PATH + im.get().getFileName()).toPath());

        return images;
    }

    public String setProfilePic(Long teacherId, MultipartFile file) throws IOException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException(
                        "teacher with id " + teacherId + " does not exist"
                ));

        Teacher savedData;
        if(file != null){
            String filename = UUID.randomUUID().toString() + ".jpg";
            Path saveTO = Paths.get(PROFILE_PICTURES_FOLDER_PATH + filename);

            teacher.setFileName(filename);
            teacher.setFilePath(String.valueOf(saveTO));

            savedData =  teacherRepository.save(teacher);

            Files.copy(file.getInputStream(), saveTO);

        } else throw new IllegalStateException(
                "cant save file"
                );

        if(savedData!=null) return "pic added successfully";
        return null;
    }

    public List<Object> getCommentsById(Long teacherId) {
        return ratingRepository.getByTeacherId(teacherId);
    }

    public Double getFinalRatingByTeacherId(Long teacherId) {
        return ratingRepository.getFinalRatingByTeacherId(teacherId);
    }
}
