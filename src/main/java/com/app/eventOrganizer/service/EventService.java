package com.app.eventOrganizer.service;

import com.app.eventOrganizer.Dto.*;
import com.app.eventOrganizer.Dto.EventCheckOutDto;
import com.app.eventOrganizer.mapper.*;
import com.app.eventOrganizer.model.*;
import com.app.eventOrganizer.repository.EventRepository;
import com.app.eventOrganizer.repository.UserNotificationRepository;
import com.app.eventOrganizer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ExpenseModelMapper expenseModelMapper;
    private final EventModelMapper mapper;
    private final UserDataService userDataService;
    private final UserRepository userRepository;
    private final ParticipantModelMapper participantModelMapper;
    private final UserNotificationRepository userNotificationRepository;
    private final UserNotificationMapper userNotificationMapper;

    @Transactional
    public EventModelDto registerEvent(EventRegistrationDto eventRegistrationDto) {

        EventModel eventModel = mapper.toEntity(eventRegistrationDto);
        Long createdBy = userDataService.getAuthenticatedUserID().getUserId();
        eventModel.setCreatedBy(createdBy);

        EventModel savedEvent = eventRepository.save(eventModel);

        UserModel userModel = userRepository.findById(createdBy).get();

        savedEvent.setParticipant(new HashSet<>());
        savedEvent.getParticipant().add(userModel);

        return mapper.toDto(savedEvent);
    }

    @Transactional
    public Set<ExpenseModelDto> addExpense(Long eventId, ExpenseRegistrationDto expenseRegistrationDto) {
        EventModel eventModel = eventRepository.getReferenceById(eventId);
        // validateEventStatus(eventModel);

        if (eventModel.getParticipant().contains(userDataService.getAuthenticatedUserID())){

        Long createdBy = userDataService.getAuthenticatedUserID().getUserId();
        ExpenseModel expenseModel = expenseModelMapper.toEntity(expenseRegistrationDto);

        if (eventModel.getExpenses() == null || eventModel.getExpenses().isEmpty()) {
            eventModel.setExpenses(new HashSet<>());
        }
        eventModel.getExpenses().add(expenseModel);

        expenseModel.setCreatedBy(createdBy);

        return expenseModelMapper.toDtoSet(eventRepository.save(eventModel).getExpenses());
        } else {
            throw new RuntimeException(String.format("You are not allowed to add new expense in this event, since you are not participant of this event with ID - %s!", eventModel.getEventId()));
        }
    }

//    private void validateEventStatus(EventModel eventModel){
//        if (eventModel.getIsClosed().equals(true)){
//            throw new RuntimeException("Event is closed! You are not allowed to make any changes in closed events!");
//        }
//    }

    public Set<ParticipantModelDto> inviteUser(ParticipantModelDto participantModelDto) {
        EventModel eventModel = eventRepository.getReferenceById(participantModelDto.getEventId());
       // validateEventStatus(eventModel);
        if (eventModel.getParticipant().contains(userDataService.getAuthenticatedUserID())){
            Optional<UserModel> invitedUserOptional = userRepository.findByEmail(participantModelDto.getEmail());

            if (invitedUserOptional.isPresent()) {
                UserModel userModel = invitedUserOptional.get();
                eventModel.getParticipant().add(userModel);

                return participantModelMapper.toDtoSet(eventRepository.save(eventModel).getParticipant(), eventModel);

            } else {
                throw new IllegalArgumentException("User not found for email: " + participantModelDto.getEmail());
            }
        } else {
            throw new RuntimeException(String.format("You are not allowed to invite user, since you are not participant of this event with ID - %s!", eventModel.getEventId()));
        }
    }

    @Transactional
    public EventCheckOutDto checkOut(Long eventId) {
        EventModel eventModel = eventRepository.getReferenceById(eventId);
        if (!eventModel.getCreatedBy().equals(userDataService.getAuthenticatedUserID().getUserId())){
            throw new RuntimeException(String.format("You are not allowed to make check out operation for event with ID %s, since you are not CREATOR of this event", eventId));
        }
        eventModel.setIsClosed(true);

        EventCheckOutDto eventCheckOutDto = new EventCheckOutDto();

        BigDecimal totalExpenses = BigDecimal.valueOf(0.00);
        BigDecimal totalParticipants = BigDecimal.valueOf(eventModel.getParticipant().size());

        List<BigDecimal> expenses =  eventModel.getExpenses().stream().map(ExpenseModel::getAmount).collect(Collectors.toList());

        for(BigDecimal expense: expenses){
            totalExpenses = totalExpenses.add(expense);
        }
        BigDecimal averageExpensesPerParticipant =  totalExpenses.divide(totalParticipants, 2);


        eventCheckOutDto.setTotalExpenses(totalExpenses);
        eventCheckOutDto.setTotalParticipants(totalParticipants);
        eventCheckOutDto.setAverageExpensesPerParticipant(averageExpensesPerParticipant);

        Set<ExpenseModel> eventModelExpenses = eventModel.getExpenses();

        HashMap<Long, BigDecimal> expensesPerUser = new HashMap<>();
        HashMap<Long, BigDecimal> accountReconciliations = new HashMap<>();

        for (ExpenseModel expenseModel: eventModelExpenses) {
            if (expensesPerUser.containsKey(expenseModel.getCreatedBy())){
                expensesPerUser.put(expenseModel.getCreatedBy(), expensesPerUser.get(expenseModel.getCreatedBy()).add( expenseModel.getAmount()));
                accountReconciliations.put(expenseModel.getCreatedBy(), accountReconciliations.get(expenseModel.getCreatedBy()).add( expenseModel.getAmount()));
            } else {
                expensesPerUser.put(expenseModel.getCreatedBy(), expenseModel.getAmount());
                accountReconciliations.put(expenseModel.getCreatedBy(), expenseModel.getAmount());
            }
        }
        for(UserModel participant: eventModel.getParticipant()){
            if (!expensesPerUser.containsKey(participant.getUserId())){
                expensesPerUser.put(participant.getUserId(), BigDecimal.valueOf(0.00));
                accountReconciliations.put(participant.getUserId(), BigDecimal.valueOf(0.00));
            }
        }
        for (Map.Entry<Long, BigDecimal> entry : accountReconciliations.entrySet()) {
            accountReconciliations.put(entry.getKey(), entry.getValue().subtract(averageExpensesPerParticipant));
        }

        eventCheckOutDto.setExpensesPerUser(expensesPerUser);
        //////
        List<Long> usersWithNegativeBalance = new ArrayList<>();
        for (Map.Entry<Long, BigDecimal> entry : accountReconciliations.entrySet()) {
            BigDecimal balance = entry.getValue();
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                usersWithNegativeBalance.add(entry.getKey());
            }
        }

        HashMap<Long, HashMap<Long, BigDecimal>> owedAmounts = new HashMap<>();
        for (Long debtor : usersWithNegativeBalance) {
            owedAmounts.put(debtor, new HashMap<>());

            BigDecimal debtorBalance = accountReconciliations.get(debtor).abs();

            for (Map.Entry<Long, BigDecimal> entry : accountReconciliations.entrySet()) {
                Long creditor = entry.getKey();
                BigDecimal creditorBalance = entry.getValue();

                if (creditorBalance.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal amountOwed = creditorBalance.min(debtorBalance);
                    debtorBalance = debtorBalance.subtract(amountOwed);
                    accountReconciliations.put(debtor, debtorBalance.negate());
                    accountReconciliations.put(creditor, creditorBalance.subtract(amountOwed));
                    owedAmounts.get(debtor).put(creditor, amountOwed);
                }
            }
        }

        eventCheckOutDto.setOwedAmounts(owedAmounts);
        /////
        sendNotification(eventModel, eventCheckOutDto);

        return eventCheckOutDto;
    }

    public List<UserNotificationDto> getMyInbox(Long userId){
        if (!userDataService.getAuthenticatedUserID().getUserId().equals(userId)){
            throw new RuntimeException("You are not allowed to see notifications of other users!");
        }
        List<UserNotification> notifications = userNotificationRepository.findAllByUserId(userId);

        return userNotificationMapper.toDtos(notifications);
    }

    private void sendNotification(EventModel eventModel, EventCheckOutDto eventCheckOutDto){
        Set<UserModel> userModels = eventModel.getParticipant();
        Long closedBy = eventModel.getCreatedBy();
        ArrayList<UserNotification> notifications = new ArrayList<>();

        for (UserModel userModel: userModels){
            notifications.add(UserNotification.builder()
                    .eventId(eventModel.getEventId())
                    .userId(userModel.getUserId())
                    .notification(String.format("Event with ID - %s has been closed by user with ID %s!", eventModel.getEventId(), closedBy))
                    .build());
        }
        userNotificationRepository.saveAll(notifications);
    }

    public List<EventModelDto> findAllMyEvents(Long userId){
        if (!userDataService.getAuthenticatedUserID().getUserId().equals(userId)){
            throw new RuntimeException("You are not allowed to see other user's events!");
        }
        Set<EventModel> event = userRepository.getReferenceById(userId).getEvent();
        List<EventModelDto> events = event.stream().map(mapper::toDto).collect(Collectors.toList());

        return events;
    }

    public List<ExpenseModelDto> findEventExpenses( Long userId, Long eventId){
        if (!userDataService.getAuthenticatedUserID().getUserId().equals(userId)){
            throw new RuntimeException("You are not allowed to see other user's events!");
        }

        return eventRepository.getReferenceById(eventId).getExpenses().stream()
                .map(expenseModelMapper::toDto)
                .collect(Collectors.toList());
    }
}
