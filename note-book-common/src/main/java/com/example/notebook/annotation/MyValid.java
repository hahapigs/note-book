package com.example.notebook.annotation;

import com.example.notebook.handler.ConstantValidatorHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
// 指定注解的处理类
@Constraint(validatedBy = {ConstantValidatorHandler.class})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValid {

    String message() default "{constraint.default.const.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}
