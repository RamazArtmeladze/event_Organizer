package com.app.eventOrganizer.controller;


import com.app.eventOrganizer.model.TestModel;
import com.app.eventOrganizer.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final TestService testService;

    @GetMapping("/health")
    public List<TestModel> getAllTestUsers() {
        return testService.getInfo();
    }
}
