package com.artel_platform.bank.payment.validation;

import com.artel_platform.bank.payment.dto.AmountDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AmountValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AmountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "value", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "currency", "field.required");
    }
}
