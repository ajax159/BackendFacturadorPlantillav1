package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacTipoDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacTipoDocumentoRepository;
@Component
public class FacTipoDocumentoValidator implements Validator{
    @Autowired
    FacTipoDocumentoRepository valFacTipoDocumento;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacTipoDocumentoModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacTipoDocumentoModel facTipoDocumentoModel = (FacTipoDocumentoModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("tpdDescripcion")) {
                if(valFacTipoDocumento.findBytpdDescripcion(facTipoDocumentoModel.getTpdDescripcion())!= null) {
                    errors.rejectValue("tpdDescripcion", "fac_TipoDocumento.tpdDescripcion.Only","Ya existe una TipoDocumento con ese nombre");
                }
            }
    }
}
