package com.example.notebook.handler;

import com.example.notebook.annotation.MyValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解的处理类
 * @author zhaohongliang
 * @description
 * @date 14:43:00 2019-03-22
 */
public class ConstantValidatorHandler implements ConstraintValidator<MyValid, String> {

    private String content;

    @Override
    public void initialize(MyValid myValid) {
        this.content = myValid.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return content.equals(value);
    }
}
