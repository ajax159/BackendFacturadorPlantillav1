package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacMedioPagoModel;
import com.servicex.facturador.repositories._m_facturador.FacMedioPagoRepository;
@Component
public class FacMedioPagoValidator implements Validator{
    @Autowired
    FacMedioPagoRepository valFacMedioPago;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacMedioPagoModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacMedioPagoModel facMedioPagoModel = (FacMedioPagoModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("fpaDescripcion")) {
                if(valFacMedioPago.findBympaDescripcion(facMedioPagoModel.getMpaDescripcion())!= null) {
                    errors.rejectValue("fpaDescripcion", "fac_MedioPago.fpaDescripcion.Only","Ya existe una MedioPago con ese nombre");
                }
            }
    }
}
