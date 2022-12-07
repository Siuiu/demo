package com.ly.demo.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author liuyang
 * @Date 2022-11-25 17:09
 **/
public class isDefaultValidator implements ConstraintValidator<isDefault,String> {

    @Override
    public void initialize(isDefault constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
