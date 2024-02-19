package com.servicex.facturador.config._api.exceptions;

// import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;


public class DeleteException extends BindException {
    public DeleteException(Exception errores) {
        super(new BeanPropertyBindingResult(new Object(), "ApiDeleteException"));
    }
}
