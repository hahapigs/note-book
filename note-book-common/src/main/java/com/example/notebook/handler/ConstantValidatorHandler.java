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
        //  获取设置的字段值
        this.content = myValid.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 判断参数是否等于设置的字段值，返回结果
        return content.equals(value);
    }
}
