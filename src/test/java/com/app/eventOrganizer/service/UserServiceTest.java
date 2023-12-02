package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.mapper.UserModelMapper;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        UserModelMapper mapper = new UserModelMapper(passwordEncoder);
        userService = new UserService(userRepository, mapper);
    }
    @Test
    void registerUser() {
        UserRegistrationDto registrationDto = UserRegistrationDto.builder()
                .email("user1@example.com")
                .firstName("John")
                .lastName("Doe")
                .password("password1")
                .passwordConfirmation("password1")
                .build();

        UserModel userModel = UserModel.builder()
                .email(registrationDto.getEmail())
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .password(registrationDto.getPassword())
                .build();

        UserModelDto expectedUserDto = UserModelDto.builder()
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .build();

        Mockito.when(userRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        UserModelDto actualUserDto = userService.registerUser(registrationDto);

        Assertions.assertEquals(expectedUserDto.getEmail(), actualUserDto.getEmail());
        Assertions.assertEquals(expectedUserDto.getFirstName(), actualUserDto.getFirstName());
        Assertions.assertEquals(expectedUserDto.getLastName(), actualUserDto.getLastName());
    }
}