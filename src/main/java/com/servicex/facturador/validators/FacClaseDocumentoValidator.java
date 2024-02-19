package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacClaseDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacClaseDocumentoRepository;
@Component
public class FacClaseDocumentoValidator implements Validator{
    @Autowired
    FacClaseDocumentoRepository valFacClaseDocumento;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacClaseDocumentoModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacClaseDocumentoModel facClaseDocumentoModel = (FacClaseDocumentoModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("cldDescripcion")) {
                if(valFacClaseDocumento.findBycldDescripcion(facClaseDocumentoModel.getCldDescripcion())!= null) {
                    errors.rejectValue("cldDescripcion", "fac_clasedocumento.cldDescripcion.Only","Ya existe una descripcion con ese nombre");
                }
            }
    }
}
