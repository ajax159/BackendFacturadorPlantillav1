package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.servicex.facturador.models._m_facturador.FacCajaModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaRepository;

//import java.util.List;

@Component
public class FacCajaValidatorApi implements Validator{
    @Autowired
    FacCajaRepository valFaccaja;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacCajaModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacCajaModel facCajaModel = (FacCajaModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("cajDescripcion")) {
                if(valFaccaja.findBycajDescripcion(facCajaModel.getCajDescripcion())!= null) {
                    errors.rejectValue("cajDescripcion", "fac_caja.cajDescripcion.Only","Ya existe una caja con ese nombre");
                }
            }
        }
}
