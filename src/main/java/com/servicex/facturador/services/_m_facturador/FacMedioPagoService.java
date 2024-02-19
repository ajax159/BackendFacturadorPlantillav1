package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacMedioPagoModel;
import com.servicex.facturador.repositories._m_facturador.FacMedioPagoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacMedioPagoService {
    @Autowired
    private FacMedioPagoRepository datFacMedioPago;

    @Transactional(readOnly = true)
    public List<FacMedioPagoModel> findAll() {
        return (List<FacMedioPagoModel>) datFacMedioPago.findAll();
    }

    public FacMedioPagoModel save(FacMedioPagoModel fac_mediopago) throws SaveException {
        try {
            return datFacMedioPago.save(fac_mediopago);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMedioPagoModel findOne(Long id) {
        return datFacMedioPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró clase documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacMedioPago.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la MedioPago con ID " + id));
        // try {
        // datFacMedioPago.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacMedioPagoModel facMedioPagoModel = datFacMedioPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la MedioPago con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facMedioPagoModel.setGlbEstadoEstId(0L);

        try {
            datFacMedioPago.save(facMedioPagoModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMedioPagoModel buscarPordescripcion(String mpaDescripcion) throws NotFoundException {
        Optional<FacMedioPagoModel> facMediopago = Optional.ofNullable(datFacMedioPago.findBympaDescripcion(mpaDescripcion));
        if (facMediopago.isEmpty()){
            throw new NotFoundException("No se encontró el documento con ID " + mpaDescripcion);
        }
        return facMediopago.get();
    }

    public FacMedioPagoModel update(Long id, FacMedioPagoModel fac_MedioPago) throws SaveException {
        datFacMedioPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_MedioPago.setMpaId(id);;
        return datFacMedioPago.save(fac_MedioPago);
    }

    public FacMedioPagoModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacMedioPago.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
