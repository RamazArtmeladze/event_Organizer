package com.app.eventOrganizer.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class EventCheckOutDto {
    private BigDecimal totalExpenses;
    private BigDecimal totalParticipants;
    private BigDecimal averageExpensesPerParticipant;
    private HashMap<Long, BigDecimal> expensesPerUser;
    private HashMap<Long, HashMap<Long, BigDecimal>> owedAmounts;
}
