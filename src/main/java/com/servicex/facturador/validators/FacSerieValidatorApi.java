package com.servicex.facturador.validators;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.servicex.facturador.models._m_facturador.FacSerieModel;
import com.servicex.facturador.repositories._m_facturador.FacSerieRepository;
@Component
public class FacSerieValidatorApi implements Validator{
    @Autowired
    FacSerieRepository valFacserie;

    @Override
    public boolean supports(Class<?> clazz) {
        return FacSerieModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacSerieModel facSerieModel = (FacSerieModel)target;

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(target);

        if (beanWrapper.isWritableProperty("serSerie")) {
                if(valFacserie.findByserSerie(facSerieModel.getSerSerie())!= null) {
                    errors.rejectValue("serSerie", "fac_serie.serSerie.Only","Ya existe una serie con ese nombre");
                }
            }
    }
}
