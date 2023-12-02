package com.app.eventOrganizer.validation;

import com.app.eventOrganizer.Dto.UserModelDto;
import com.app.eventOrganizer.Dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationMatchValidator implements ConstraintValidator<PasswordConfirmationMatch, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext context) {
        if (userRegistrationDto == null) {
            return true;
        }
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordConfirmation());
    }
}
