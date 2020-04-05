package com.neu.exception.general;

import com.neu.exception.BaseException;
import org.springframework.validation.BindingResult;

public class FormValidatorException extends BaseException {
    public FormValidatorException(BindingResult bindingResult) {
        this.setMessage(bindingResult.getFieldError().getField() +
                bindingResult.getFieldError().getDefaultMessage());
        this.setCode(101);
    }
}
