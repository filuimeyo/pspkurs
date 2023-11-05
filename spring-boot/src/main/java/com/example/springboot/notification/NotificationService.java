package com.example.springboot.notification;

import com.example.springboot.student.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Integer getNotViewedNotificationCount(Long userId){
        return notificationRepository.getNotViewedCount(userId);
    }

    public List<Notification> getByUserId(Long userId){
        return notificationRepository.findNotificationsByUserId(userId);
    }


    public Notification createNotification(Notification notification){
        notification.setCreatedAt(LocalDateTime.now());
        notification.setIsViewed(false);
        return notificationRepository.save(notification);
    }


    public Optional<Notification> getById(Long id) {
        return notificationRepository.findById(id);
    }

    public void setViewed(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "notif with id " + id + " does not exist"
                ));
        if(!notification.getIsViewed()){
           notification.setIsViewed(true);
           notificationRepository.save(notification);
        }


    }
}
