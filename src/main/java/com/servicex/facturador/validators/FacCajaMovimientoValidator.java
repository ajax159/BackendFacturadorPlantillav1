package com.servicex.facturador.validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.servicex.facturador.models._m_facturador.FacMovimientoCajaModel;
import com.servicex.facturador.repositories._m_facturador.FacMovimientoCajaRepository;

//import java.util.List;

@Component
public class FacCajaMovimientoValidator implements Validator{
    @Autowired
    FacMovimientoCajaRepository valFacmovcaja;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacMovimientoCajaModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacMovimientoCajaModel facMovimientoCajaModel = (FacMovimientoCajaModel)target;

        if (valFacmovcaja.existsByFacCajaCajIdAndCreatedByAndMcaFechaapertura(
                facMovimientoCajaModel.getFaccajacajId(), facMovimientoCajaModel.getCreatedBy(), facMovimientoCajaModel.getMcaFechaapertura())) {
            errors.rejectValue("faccajacajId", "fac_movimientocaja.duplicate",
                    "La caja seleccionada se encuentra abierta en la fecha seleccionada.");
        }
    }

    
}
