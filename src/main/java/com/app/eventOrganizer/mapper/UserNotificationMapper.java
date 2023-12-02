package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.UserNotificationDto;
import com.app.eventOrganizer.model.UserNotification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserNotificationMapper {

    public UserNotificationDto toDto(UserNotification userNotification){
        return UserNotificationDto.builder()
                .userId(userNotification.getUserId())
                .id(userNotification.getId())
                .eventId(userNotification.getEventId())
                .notification(userNotification.getNotification())
                .build();
    }

    public List<UserNotificationDto> toDtos(List<UserNotification> userNotifications){
        return userNotifications.stream().map(userNotification -> toDto(userNotification)).collect(Collectors.toList());
    }
}
