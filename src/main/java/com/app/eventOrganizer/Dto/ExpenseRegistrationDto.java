package com.app.eventOrganizer.Dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExpenseRegistrationDto {
    private BigDecimal amount;
    private String description;
}
