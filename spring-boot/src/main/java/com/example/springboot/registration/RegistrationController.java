package com.example.springboot.registration;

import com.example.springboot.registration.requestModels.StudentRegistrationRequest;
import com.example.springboot.registration.requestModels.TeacherRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService   registrationService;

 /*   @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return  registrationService.register(request);
    }
*/
    @PostMapping(path = "student")
    public String registerStudent(@RequestBody StudentRegistrationRequest request){
        return  registrationService.registerStudent(request);
    }

    @PostMapping(path = "teacher")
    public String registerTeacher(@RequestBody TeacherRegistrationRequest request){
        return  registrationService.registerTeacher(request);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
