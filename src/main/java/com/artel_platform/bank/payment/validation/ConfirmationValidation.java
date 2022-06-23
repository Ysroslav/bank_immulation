package com.artel_platform.bank.payment.validation;

import com.artel_platform.bank.payment.dto.AmountDTO;
import com.artel_platform.bank.payment.dto.ConfirmationDTO;
import com.artel_platform.bank.payment.dto.ConfirmationInputDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ConfirmationValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ConfirmationInputDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "type", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "return_url", "field.required");
    }
}
