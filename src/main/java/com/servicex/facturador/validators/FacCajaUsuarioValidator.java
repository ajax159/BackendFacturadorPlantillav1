package com.servicex.facturador.validators;
//import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
// import org.springframework.validation.FieldError;
// import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacCajaUsuarioModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaUsuarioRepository;
//import java.util.List;
@Component
public class FacCajaUsuarioValidator implements Validator {
    @Autowired
    private FacCajaUsuarioRepository facCajaUsuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacCajaUsuarioModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacCajaUsuarioModel facCajaUsuario = (FacCajaUsuarioModel) target;

        if (facCajaUsuarioRepository.existsByFacUsuarioUsuIdAndFacCajaCajIdAndGecIdAndEmpId(
                facCajaUsuario.getFacUsuarioUsuId(), facCajaUsuario.getFacCajaCajId(), facCajaUsuario.getGecId(), facCajaUsuario.getEmpId())) {
            errors.rejectValue("facUsuarioUsuId", "fac_cajausuario.duplicate",
                    "Ya existe un registro con esta combinación de usuario y caja.");
        }


    }
}
