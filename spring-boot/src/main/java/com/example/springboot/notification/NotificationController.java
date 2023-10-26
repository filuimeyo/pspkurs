package com.example.springboot.notification;

import com.example.springboot.teacher.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration/notification")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;


    @GetMapping(path = "/{id}")
    public List<Notification> getNotificationByUserId(@PathVariable("id") Long id) {
        return notificationService.getByUserId(id);
    }

    @GetMapping(path = "user/{id}")
    public Optional<Notification> getNotificationId(@PathVariable("id") Long id) {
        return notificationService.getById(id);
    }

    @PutMapping(path = "user/{id}")
    public void setNotificationViewed(@PathVariable("id") Long id) {
        notificationService.setViewed(id);
    }

}
