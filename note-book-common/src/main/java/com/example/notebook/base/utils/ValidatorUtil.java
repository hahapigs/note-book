package com.example.notebook.base.utils;

import com.example.notebook.exception.CommonException;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 实体参数校验工具类
 * @author zhaohongliang
 * @description
 * @date 13:21:00 2019-03-22
 */
public class ValidatorUtil {

    private static Validator validator = ((HibernateValidatorConfiguration) Validation
            .byProvider(HibernateValidator.class).configure()).failFast(true).buildValidatorFactory().getValidator();

    /**
     * 实体校验
     */
    public static <T> void validate(T object) throws CommonException {
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(object, new Class[0]);
        if (constraintViolationSet.size() > 0) {
            ConstraintViolation<T> constraintViolation = constraintViolationSet.iterator().next();
            throw new CommonException();
        }
    }
}
