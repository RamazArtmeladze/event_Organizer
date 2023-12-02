package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;
import com.app.eventOrganizer.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.eventOrganizer.model.UserModel;
import com.app.eventOrganizer.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;

    public UserModelDto registerUser(UserRegistrationDto userRegistrationDto) {

        UserModel userModel = mapper.toEntity(userRegistrationDto);
        UserModel savedUserModel = userRepository.save(userModel);

        return mapper.toDto(savedUserModel);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(userModel.getEmail(), userModel.getPassword(), Collections.emptyList());
    }
}
