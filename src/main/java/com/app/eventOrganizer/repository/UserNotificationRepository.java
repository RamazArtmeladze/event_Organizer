package com.app.eventOrganizer.repository;

import com.app.eventOrganizer.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findAllByUserId(Long userID);
}
