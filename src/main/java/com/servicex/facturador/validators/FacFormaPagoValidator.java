package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacFormaPagoModel;
import com.servicex.facturador.repositories._m_facturador.FacFormaPagoRepository;
@Component
public class FacFormaPagoValidator implements Validator{
    @Autowired
    FacFormaPagoRepository valFacFormaPago;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacFormaPagoModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacFormaPagoModel facFormaPagoModel = (FacFormaPagoModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("fpaDescripcion")) {
                if(valFacFormaPago.findByfpaDescripcion(facFormaPagoModel.getFpaDescripcion())!= null) {
                    errors.rejectValue("fpaDescripcion", "fac_FormaPago.fpaDescripcion.Only","Ya existe una FormaPago con ese nombre");
                }
            }
    }
}
