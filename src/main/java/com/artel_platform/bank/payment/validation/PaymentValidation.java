package com.artel_platform.bank.payment.validation;

import com.artel_platform.bank.payment.dto.PaymentInputDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class PaymentValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentInputDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "amount", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "capture", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "confirmation", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "description", "field.required");
    }
}
