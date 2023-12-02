package com.app.eventOrganizer.controller;

import com.app.eventOrganizer.Dto.*;
import com.app.eventOrganizer.Dto.EventCheckOutDto;
import com.app.eventOrganizer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<EventModelDto> createEventModel(@Valid  @RequestBody EventRegistrationDto eventRegistrationDto) {
        EventModelDto eventModelDto = eventService.registerEvent(eventRegistrationDto);

        return new ResponseEntity<>(eventModelDto, HttpStatus.CREATED);
    }

    @PostMapping("events/{eventId}/expenses")
    public ResponseEntity<Set<ExpenseModelDto>> addExpense(@PathVariable Long eventId, @RequestBody ExpenseRegistrationDto expenseRegistrationDto) {
        Set<ExpenseModelDto> expenseModelDto = eventService.addExpense(eventId, expenseRegistrationDto);

        return new ResponseEntity<>(expenseModelDto, HttpStatus.CREATED);
    }

    @PostMapping("/events/invite")
    public ResponseEntity<Set<ParticipantModelDto>> inviteUser(@RequestBody ParticipantModelDto participantModelDto) {
        Set<ParticipantModelDto> participantDto = eventService.inviteUser(participantModelDto);

        return new ResponseEntity<>(participantDto, HttpStatus.OK);
    }

    @PutMapping("events/{eventId}")
    public ResponseEntity<EventCheckOutDto> closeEvent(@PathVariable Long eventId){
        EventCheckOutDto eventCheckOutDto = eventService.checkOut(eventId);

        return new ResponseEntity<>(eventCheckOutDto, HttpStatus.OK);
    }

    @GetMapping("events/inbox/{userId}")
    public ResponseEntity<List<UserNotificationDto>> getInbox(@PathVariable Long userId) {
        List<UserNotificationDto> userNotificationDto = eventService.getMyInbox(userId);

        return new ResponseEntity<>(userNotificationDto, HttpStatus.OK);
    }

    @GetMapping("events/participant/{userId}")
    public ResponseEntity<List<EventModelDto>> getAllMyEvents(@PathVariable Long userId) {
        List<EventModelDto> events = eventService.findAllMyEvents(userId);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("events/participant/{userId}/{eventId}")
    public ResponseEntity<List<ExpenseModelDto>> getAllMyEvents(@PathVariable Long userId, @PathVariable Long eventId) {
        List<ExpenseModelDto> expenses = eventService.findEventExpenses(userId, eventId);

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}