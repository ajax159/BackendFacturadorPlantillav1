package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacClienteModel;
import com.servicex.facturador.repositories._m_facturador.FacClienteRepository;
@Component
public class FacClienteValidator implements Validator{
    @Autowired
    FacClienteRepository valFaccliente;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacClienteModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacClienteModel facClienteModel = (FacClienteModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("cliNdocumento")) {
                if(valFaccliente.findBycliNdocumento(facClienteModel.getCliNdocumento())!= null) {
                    errors.rejectValue("cliNdocumento", "fac_cliente.cliNdocumento.Only","Ya existe una Cliente con ese numero");
                }
            }
    }
}
