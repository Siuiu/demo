package com.ly.demo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author liuyang
 * @Date 2023/8/14 11:39
 *
 * Long类型长度最大20位
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { NumberLengthValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface NumberLength {
    String message() default "字段长度超限";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int maxLength();
}