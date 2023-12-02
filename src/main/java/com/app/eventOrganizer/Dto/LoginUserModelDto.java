package com.app.eventOrganizer.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class LoginUserModelDto {
    private String email;

    private String password;
}
