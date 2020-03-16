package com.gyj.gx.base.util.validator;

import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.returns.RespCode;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

public class ValidatorBeanFactory {

    private static final Validator validator;

    static{
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    public static void validate(Object t, Class clazz) {
        Set<ConstraintViolation<Object>> requestViolations = validator.validate(t, clazz);
        businessException(requestViolations);
    }

    public static void validateList(List<?> list, Class clazz) {
        if (list == null) {
           throw new IllegalArgumentException("empty-paramter");
        }
        for (Object t : list) {
            Set<ConstraintViolation<Object>> requestViolations = validator.validate(t, clazz);
            businessException(requestViolations);
        }
    }

    private static void businessException(Set<ConstraintViolation<Object>> requestViolations){
        if (!requestViolations.isEmpty()) {
            StringBuffer errorMsg = new StringBuffer();
            for (ConstraintViolation<Object> constraintViolation : requestViolations) {
                errorMsg.append(constraintViolation.getMessageTemplate()); break;
            }
            throw new BusinessException(RespCode.PARAMETERS_ERROR,errorMsg.toString());
        }
    }

}
