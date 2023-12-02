package com.app.eventOrganizer.controller;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModelDto> createUserModel(@Valid  @RequestBody UserRegistrationDto userRegistrationDto) {
        UserModelDto userModelDto = userService.registerUser(userRegistrationDto);
        return new ResponseEntity<>(userModelDto, HttpStatus.CREATED);
    }
}
