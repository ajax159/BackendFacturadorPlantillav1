package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacFormaPagoModel;
import com.servicex.facturador.repositories._m_facturador.FacFormaPagoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacFormaPagoService {
    @Autowired
    private FacFormaPagoRepository datFacFormaPago;

    @Transactional(readOnly = true)
    public List<FacFormaPagoModel> findAll() {
        return (List<FacFormaPagoModel>) datFacFormaPago.findAll();
    }

    public FacFormaPagoModel save(FacFormaPagoModel fac_formapago) throws SaveException {
        try {
            return datFacFormaPago.save(fac_formapago);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacFormaPagoModel findOne(Long id) {
        return datFacFormaPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró clase documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacFormaPago.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la FormaPago con ID " + id));
        // try {
        // datFacFormaPago.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacFormaPagoModel facFormaPagoModel = datFacFormaPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la FormaPago con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facFormaPagoModel.setGlbEstadoEstId(0L);

        try {
            datFacFormaPago.save(facFormaPagoModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacFormaPagoModel buscarPorId(String fpaDescripcion) throws NotFoundException {
        Optional<FacFormaPagoModel> facFormapago = Optional.ofNullable(datFacFormaPago.findByfpaDescripcion(fpaDescripcion));
        if (facFormapago.isEmpty()){
            throw new NotFoundException("No se encontró el documento con ID " + fpaDescripcion);
        }
        return facFormapago.get();
    }

    public FacFormaPagoModel update(Long id, FacFormaPagoModel fac_FormaPago) throws SaveException {
        datFacFormaPago.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_FormaPago.getFpaId();
        return datFacFormaPago.save(fac_FormaPago);
    }

    public FacFormaPagoModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacFormaPago.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
