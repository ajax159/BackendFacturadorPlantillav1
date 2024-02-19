package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
//import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaDocumentoDTO;
import com.servicex.facturador.dtos.FacCajaDocumentoNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaDocumentoRepository;

import java.util.*;
@Service
public class FacCajaDocumentoService {
    @Autowired
    private FacCajaDocumentoRepository datFacCajaDocumento;

    @Transactional(readOnly = true)
    public List<FacCajaDocumentoModel> findAll() {
        return (List<FacCajaDocumentoModel>) datFacCajaDocumento.findAll();
    }

    public FacCajaDocumentoModel save(FacCajaDocumentoModel fac_cajadocumento) throws SaveException {
        try {
            return datFacCajaDocumento.save(fac_cajadocumento);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacCajaDocumentoModel findOne(Long id) {
        return datFacCajaDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        datFacCajaDocumento.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró la caja documento con ID " + id));
        try {
            datFacCajaDocumento.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    // @Transactional(readOnly = true)
    // public FacCajaDocumentoModel buscarPorId(Long cjdId) throws NotFoundException {
    //     Optional<FacCajaDocumentoModel> facCajadocumento = Optional.ofNullable(datFacCajaDocumento.findBycjdId(cjdId));
    //     if (facCajadocumento.isEmpty()){
    //         throw new NotFoundException("No se encontró el documento con ID " + cjdId);
    //     }
    //     return facCajadocumento.get();
    // }

    public FacCajaDocumentoModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacCajaDocumento.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }

    public ArrayList<FacCajaDocumentoDTO> getIdCaja(Long idcaja){
        return datFacCajaDocumento.getIdCaja(idcaja);
    }
    public ArrayList<FacCajaDocumentoNotDTO> getnotIdCaja(Long idcaja){
        return datFacCajaDocumento.getnotIdCaja(idcaja);
    }
}
