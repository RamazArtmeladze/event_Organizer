package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.EventModelDto;
import com.app.eventOrganizer.Dto.EventRegistrationDto;
import com.app.eventOrganizer.model.EventModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EventModelMapper {

    public EventModelDto toDto(EventModel eventModel) {

        return EventModelDto.builder()
                .id(eventModel.getEventId())
                .name(eventModel.getName())
                .startDate(eventModel.getStartDate())
                .endDate(eventModel.getEndDate())
                .location(eventModel.getLocation())
                .description(eventModel.getDescription())
                .estimateBudget(eventModel.getEstimateBudget())
                .build();
    }

    public EventModel toEntity (EventRegistrationDto eventRegistrationDto) {
        return EventModel.builder()
                .name(eventRegistrationDto.getName())
                .startDate(eventRegistrationDto.getStartDate())
                .endDate(eventRegistrationDto.getEndDate())
                .location(eventRegistrationDto.getLocation())
                .description(eventRegistrationDto.getDescription())
                .estimateBudget((eventRegistrationDto.getEstimateBudget()))
                .build();
    }
}
