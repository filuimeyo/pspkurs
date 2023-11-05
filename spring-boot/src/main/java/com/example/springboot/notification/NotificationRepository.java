package com.example.springboot.notification;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT n FROM Notification n WHERE n.appUser.id = ?1")
    List<Notification> findNotificationsByUserId(Long userId);

    @Query("SELECT count(n) FROM Notification n WHERE n.appUser.id = ?1 and n.isViewed = false")
    Integer getNotViewedCount(Long userId);
}
