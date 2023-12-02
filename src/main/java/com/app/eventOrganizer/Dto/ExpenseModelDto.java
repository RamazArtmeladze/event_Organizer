package com.app.eventOrganizer.Dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExpenseModelDto {
    private Long id;
    private String description;
    private BigDecimal amount;
}
