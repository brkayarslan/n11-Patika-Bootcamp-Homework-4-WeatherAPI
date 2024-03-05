package com.berkayarslan.WeatherAPI.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class CityNameValidator implements ConstraintValidator<CityNameConstraint,String> {

    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        value = value.replaceAll("[^a-zA-Z]", "");

        return !value.matches(".*\\d.*") && !StringUtils.isAllBlank(value);

    }
}
