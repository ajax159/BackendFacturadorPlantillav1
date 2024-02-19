package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacClaseDocumentoModel;
import com.servicex.facturador.repositories._m_facturador.FacClaseDocumentoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacClaseDocumentoService {
    @Autowired
    private FacClaseDocumentoRepository datFacClaseDocumento;

    @Transactional(readOnly = true)
    public List<FacClaseDocumentoModel> findAll() {
        return (List<FacClaseDocumentoModel>) datFacClaseDocumento.findAll();
    }

    public FacClaseDocumentoModel save(FacClaseDocumentoModel fac_clasedocumento) throws SaveException {
        try {
            return datFacClaseDocumento.save(fac_clasedocumento);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacClaseDocumentoModel findOne(Long id) {
        return datFacClaseDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró clase documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacClaseDocumento.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la ClaseDocumento con ID " + id));
        // try {
        // datFacClaseDocumento.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacClaseDocumentoModel facClaseDocumentoModel = datFacClaseDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la ClaseDocumento con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facClaseDocumentoModel.setGlbEstadoEstId(0L);

        try {
            datFacClaseDocumento.save(facClaseDocumentoModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    public FacClaseDocumentoModel update(Long id, FacClaseDocumentoModel fac_ClaseDocumento) throws SaveException {
        datFacClaseDocumento.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_ClaseDocumento.setCldId(id);
        return datFacClaseDocumento.save(fac_ClaseDocumento);
    }

    @Transactional(readOnly = true)
    public FacClaseDocumentoModel buscarPorId(String cldDescripcion) throws NotFoundException {
        Optional<FacClaseDocumentoModel> facClasedocumento = Optional.ofNullable(datFacClaseDocumento.findBycldDescripcion(cldDescripcion));
        if (facClasedocumento.isEmpty()){
            throw new NotFoundException("No se encontró el documento con ID " + cldDescripcion);
        }
        return facClasedocumento.get();
    }

    public FacClaseDocumentoModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacClaseDocumento.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
