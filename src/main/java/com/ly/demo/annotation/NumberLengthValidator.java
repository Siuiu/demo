package com.ly.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author liuyang
 * @Date 2023/8/14 11:45
 **/
public class NumberLengthValidator implements ConstraintValidator<NumberLength, Number> {
    private Integer maxLength;

    @Override
    public void initialize(NumberLength constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value != null && value.toString().length() > maxLength) {
            return false;
        }
        return true;
    }
}
