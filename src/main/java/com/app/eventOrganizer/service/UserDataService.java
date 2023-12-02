package com.app.eventOrganizer.service;

import com.app.eventOrganizer.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import com.app.eventOrganizer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserRepository userRepository;

    public UserModel getAuthenticatedUserID() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(userEmail).orElseThrow();
    }
}
