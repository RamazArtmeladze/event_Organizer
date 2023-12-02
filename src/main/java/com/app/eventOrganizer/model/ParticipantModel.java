package com.app.eventOrganizer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "participants")
public class ParticipantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventModel event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}