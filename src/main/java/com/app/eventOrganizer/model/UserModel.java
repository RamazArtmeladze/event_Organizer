package com.app.eventOrganizer.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @ManyToMany(mappedBy = "participant")
    private Set<EventModel> event;
}
