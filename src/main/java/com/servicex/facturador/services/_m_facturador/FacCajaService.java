package com.servicex.facturador.services._m_facturador;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;

import com.servicex.facturador.models._m_facturador.FacCajaModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaRepository;

import java.util.*;

@Service
public class FacCajaService{
    @Autowired
    private FacCajaRepository datFacCaja;

    @Transactional(readOnly = true)
    public List<FacCajaModel> findAll() {
        return (List<FacCajaModel>) datFacCaja.findAll();
    }

    public FacCajaModel save(FacCajaModel fac_caja) throws SaveException {

        return datFacCaja.save(fac_caja);

    }

    @Transactional(readOnly = true)
    public FacCajaModel findOne(Long id) {
        return datFacCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacCajaModel facCajaModel = datFacCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facCajaModel.setGlbEstadoEstId(0L);

        try {
            datFacCaja.save(facCajaModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacCajaModel buscarPorNombre(String cajDescripcion) throws NotFoundException {
        Optional<FacCajaModel> facCaja = Optional.ofNullable(datFacCaja.findBycajDescripcion(cajDescripcion));
        if (facCaja.isEmpty()) {
            throw new NotFoundException("No se encontró el documento con ID " + cajDescripcion);
        }
        return facCaja.get();
    }

    public FacCajaModel update(Long id, FacCajaModel fac_caja) throws SaveException {
        datFacCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_caja.setCajId(id);
        return datFacCaja.save(fac_caja);
    }

    public ArrayList<FacCajaModel> listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacCaja.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
