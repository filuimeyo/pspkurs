package com.example.springboot.appuser;

import com.example.springboot.student.Student;
import com.example.springboot.teacher.Teacher;
import com.example.springboot.student.StudentService;
import com.example.springboot.teacher.TeacherService;
import com.example.springboot.registration.token.ConfirmationToken;
import com.example.springboot.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final StudentService studentService;
    private final TeacherService teacherService;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (userExist) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }

        String encodedPas = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPas);

        appUserRepository.save(appUser);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        //todo: send email

        return token;
    }



    public String signUpStudent(AppUser appUser, Student student) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (userExist) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }

        String encodedPas = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPas);

        appUserRepository.save(appUser);
        studentService.addNewStudent(student);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        //todo: send email

        return token;
    }


    public String signUpTeacher(AppUser appUser, Teacher teacher) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (userExist) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }

        String encodedPas = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPas);

        appUserRepository.save(appUser);
        teacherService.addNewTeacher(teacher);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        //todo: send email

        return token;
    }



    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
