package com.servicex.facturador.services._m_facturador;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacSerieModel;
import com.servicex.facturador.repositories._m_facturador.FacSerieRepository;
import java.util.*;
@Service
public class FacSerieService {
    @Autowired
    private FacSerieRepository datFacSerie;

    @Transactional(readOnly = true)
    public List<FacSerieModel> findAll() {
        return (List<FacSerieModel>) datFacSerie.findAll();
    }

    public FacSerieModel save(FacSerieModel fac_serie) throws SaveException {
        try {
            return datFacSerie.save(fac_serie);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacSerieModel findOne(Long id) {
        return datFacSerie.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró serie con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacSerieModel facSerieModel = datFacSerie.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facSerieModel.setGlbEstadoEstId(0L);

        try {
            datFacSerie.save(facSerieModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    public FacSerieModel update(Long id, FacSerieModel fac_serie) throws SaveException {
        datFacSerie.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_serie.setSerId(id);
        return datFacSerie.save(fac_serie);
    }

    @Transactional(readOnly = true)
    public FacSerieModel buscarPorSerie(String serSerie) throws NotFoundException {
        Optional<FacSerieModel> facSerie = Optional.ofNullable(datFacSerie.findByserSerie(serSerie));//
        if (facSerie.isEmpty()){
            throw new NotFoundException("No se encontró serie con ID " + serSerie);
        }
        return facSerie.get();
    }

    public ArrayList<FacSerieModel> listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacSerie.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }

}
