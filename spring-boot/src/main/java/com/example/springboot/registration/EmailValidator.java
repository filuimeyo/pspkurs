package com.example.springboot.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String string) {

        //TODO: validate email
        return true;
    }
}
