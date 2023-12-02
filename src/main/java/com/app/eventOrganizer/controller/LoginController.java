package com.app.eventOrganizer.controller;

import com.app.eventOrganizer.Dto.LoginUserModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginUserModelDto loginForm, HttpSession session) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            session.setAttribute("userDetails", userDetails);

            return "Login successful!";

        } catch (AuthenticationException e) {

            return "failed";
        }
    }
}
