package com.app.eventOrganizer.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ParticipantModelDto {
    private String email;
    private Long eventId;
}