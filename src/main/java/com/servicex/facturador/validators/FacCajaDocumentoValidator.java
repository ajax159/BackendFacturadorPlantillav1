package com.servicex.facturador.validators;
//import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacCajaDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaDocumentoRepository;
//import java.util.List;
@Component
public class FacCajaDocumentoValidator implements Validator{
    @Autowired
    private FacCajaDocumentoRepository facCajaDocumentoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacCajaDocumentoModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacCajaDocumentoModel facCajaDocumento = (FacCajaDocumentoModel) target;

        if (facCajaDocumentoRepository.existsByfacTipoDocumentoTpdIdAndFacCajaCajIdAndGecIdAndEmpId(
                facCajaDocumento.getFacTipoDocumentoTpdId(), facCajaDocumento.getFacCajaCajId(), facCajaDocumento.getGecId(), facCajaDocumento.getEmpId())) {
            errors.rejectValue("facTipoDocumentoTpdId", "fac_cajaDocumento.duplicate",
                    "Ya existe un registro con esta combinación de Datos.");
        }


    }
}
