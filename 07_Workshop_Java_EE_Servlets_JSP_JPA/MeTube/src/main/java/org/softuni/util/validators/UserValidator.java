package org.softuni.util.validators;

import org.softuni.domain.dto.RegisterUserDto;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UserValidator extends AbstractValidator<RegisterUserDto> {

    @Override
    public boolean validate(RegisterUserDto user) {
        Set<ConstraintViolation<RegisterUserDto>> violations = this.validator.validate(user);
        return violations.isEmpty() &&
                user.getConfirmPassword().equals(user.getPassword());
    }
}
