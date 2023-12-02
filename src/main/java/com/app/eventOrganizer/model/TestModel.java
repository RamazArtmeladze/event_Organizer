package com.app.eventOrganizer.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String name;

}
