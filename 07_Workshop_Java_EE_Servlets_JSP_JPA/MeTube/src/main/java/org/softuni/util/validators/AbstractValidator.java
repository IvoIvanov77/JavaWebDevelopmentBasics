package org.softuni.util.validators;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

public abstract class AbstractValidator<T> {
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    protected Validator validator;

    protected AbstractValidator() {
        this.validator = FACTORY.getValidator();
    }

    abstract boolean validate(T object);
}
