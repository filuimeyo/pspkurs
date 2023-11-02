package com.example.springboot.registration.requestModels;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeacherRegistrationRequest {
    private final String firstName;
 //   private final String lastName;
    private final Double lessonPrice;
    private final String email;
    private final String password;
}
