package com.app.eventOrganizer.mapper;


import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserModelMapper {
    @Autowired
    private PasswordEncoder passwordEncoder ;

    public UserModelDto toDto (UserModel userModel) {

        return UserModelDto.builder()
                .id(userModel.getUserId())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .build();
    }
    public  UserModel toEntity (UserRegistrationDto userRegistrationDto) {
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());

        return UserModel.builder()
                .email(userRegistrationDto.getEmail())
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .password(encodedPassword)
                .build();
    }
}
