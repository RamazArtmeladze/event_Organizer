package com.app.eventOrganizer.mapper;

import com.app.eventOrganizer.Dto.ExpenseModelDto;
import com.app.eventOrganizer.Dto.ExpenseRegistrationDto;
import com.app.eventOrganizer.model.ExpenseModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class ExpenseModelMapper {
    public ExpenseModelDto toDto (ExpenseModel expenseModel) {

        return ExpenseModelDto.builder()
                .id(expenseModel.getExpensesId())
                .amount(expenseModel.getAmount())
                .description(expenseModel.getDescription())
                .build();
    }

    public Set<ExpenseModelDto> toDtoSet(Set<ExpenseModel> expenseModels){
        return expenseModels.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public ExpenseModel toEntity (ExpenseRegistrationDto expenseRegistrationDto) {
        return ExpenseModel.builder()
                .description(expenseRegistrationDto.getDescription())
                .amount(expenseRegistrationDto.getAmount())
                .build();
    }
}
