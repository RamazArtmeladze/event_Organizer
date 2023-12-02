package com.app.eventOrganizer.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDto {
    Long id;
    private Long eventId;
    private Long userId;
    private String notification;
}
