package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.ParticipantModelDto;
import com.app.eventOrganizer.model.EventModel;
import com.app.eventOrganizer.model.ParticipantModel;
import com.app.eventOrganizer.model.UserModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class ParticipantModelMapper {

    public ParticipantModelDto toDto(UserModel userModel, EventModel eventModel){
        return ParticipantModelDto.builder()
                .email(userModel.getEmail())
                .eventId(eventModel.getEventId())
                .build();
    }

    public Set<ParticipantModelDto> toDtoSet(Set<UserModel> userModels, EventModel eventModel){
        return userModels.stream().map(userModel -> toDto(userModel, eventModel)).collect(Collectors.toSet());
    }

    public ParticipantModelDto fromModelToDto(ParticipantModel participantModel){
        return ParticipantModelDto.builder()
                .email(participantModel.getUser().getEmail())
                .eventId(participantModel.getEvent().getEventId())
                .build();
    }

    public List<ParticipantModelDto> toDtoList(List<ParticipantModel> participantModels){
        return participantModels.stream().map(this::fromModelToDto).collect(Collectors.toList());
    }
}
