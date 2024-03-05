package com.berkayarslan.WeatherAPI.controller.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {CityNameValidator.class})
@Target({METHOD,FIELD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CityNameConstraint {
    String message() default "Invalid city name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
