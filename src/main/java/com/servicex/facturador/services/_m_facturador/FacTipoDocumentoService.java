package com.servicex.facturador.services._m_facturador;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacTipoDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacTipoDocumentoRepository;

import java.util.*;

@Service
public class FacTipoDocumentoService {
    @Autowired
    private FacTipoDocumentoRepository datFacTipoDocumento;

    @Transactional(readOnly = true)
    public List<FacTipoDocumentoModel> findAll() {
        return (List<FacTipoDocumentoModel>) datFacTipoDocumento.findAll();
    }

    public FacTipoDocumentoModel save(FacTipoDocumentoModel fac_tipodocumento) throws SaveException {
        try {
            return datFacTipoDocumento.save(fac_tipodocumento);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacTipoDocumentoModel findOne(Long id) {
        return datFacTipoDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacTipoDocumentoModel facTipoDocumentoModel = datFacTipoDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facTipoDocumentoModel.setGlbEstadoEstId(0L);

        try {
            datFacTipoDocumento.save(facTipoDocumentoModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacTipoDocumentoModel buscarPorId(String tpdDescripcion) throws NotFoundException {
        Optional<FacTipoDocumentoModel> facTipodocumento = Optional.ofNullable(datFacTipoDocumento.findBytpdDescripcion(tpdDescripcion));//
        if (facTipodocumento.isEmpty()){
            throw new NotFoundException("No se encontró el documento con ID " + tpdDescripcion);
        }
        return facTipodocumento.get();
    }

    public FacTipoDocumentoModel update(Long id, FacTipoDocumentoModel fac_TipoDocumento) throws SaveException {
        datFacTipoDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_TipoDocumento.setTpdId(id);
        return datFacTipoDocumento.save(fac_TipoDocumento);
    }

    public ArrayList<FacTipoDocumentoModel> listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacTipoDocumento.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
