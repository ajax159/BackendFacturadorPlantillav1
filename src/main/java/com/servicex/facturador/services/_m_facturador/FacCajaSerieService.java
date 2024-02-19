package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
//import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaSerieDto;
import com.servicex.facturador.dtos.FacCajaSerieNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaSerieModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaSerieRepository;

import java.util.*;
@Service
public class FacCajaSerieService {
    @Autowired
    private FacCajaSerieRepository datFacCajaSerie;

    @Transactional(readOnly = true)
    public List<FacCajaSerieModel> findAll() {
        return (List<FacCajaSerieModel>) datFacCajaSerie.findAll();
    }

    public FacCajaSerieModel save(FacCajaSerieModel fac_cajaSerie) throws SaveException {
        try {
            return datFacCajaSerie.save(fac_cajaSerie);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacCajaSerieModel findOne(Long id) {
        return datFacCajaSerie.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja Serie con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        datFacCajaSerie.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró la caja Serie con ID " + id));
        try {
            datFacCajaSerie.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    // @Transactional(readOnly = true)
    // public FacCajaSerieModel buscarPorId(Long cjdId) throws NotFoundException {
    //     Optional<FacCajaSerieModel> facCajaSerie = Optional.ofNullable(datFacCajaSerie.findBycjdId(cjdId));
    //     if (facCajaSerie.isEmpty()){
    //         throw new NotFoundException("No se encontró el Serie con ID " + cjdId);
    //     }
    //     return facCajaSerie.get();
    // }

    public FacCajaSerieModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacCajaSerie.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }

    public ArrayList<FacCajaSerieDto> getIdCaja(Long idcaja){
        return datFacCajaSerie.getIdCaja(idcaja);
    }
    public ArrayList<FacCajaSerieNotDTO> getnotIdCaja(Long idcaja){
        return datFacCajaSerie.getnotIdCaja(idcaja);
    }
}
