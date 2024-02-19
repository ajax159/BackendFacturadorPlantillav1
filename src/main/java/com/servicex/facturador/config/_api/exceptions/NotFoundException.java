package com.servicex.facturador.config._api.exceptions;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;

public class NotFoundException extends BindException {
    public NotFoundException(String message) {
        super(new BeanPropertyBindingResult(new Object(), "ApiNotFoundException"));

    }
}
