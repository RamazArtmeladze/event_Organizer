package com.app.eventOrganizer.Dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EventRegistrationDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private BigDecimal estimateBudget;
    private ExpenseRegistrationDto expenses;
}
