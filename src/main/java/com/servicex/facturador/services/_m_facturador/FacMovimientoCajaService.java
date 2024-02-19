package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacMovimientoCajaDTO;
import com.servicex.facturador.models._m_facturador.FacMovimientoCajaModel;
import com.servicex.facturador.repositories._m_facturador.FacMovimientoCajaRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FacMovimientoCajaService {
    @Autowired
    private FacMovimientoCajaRepository datMovimientoCaja;

    @Transactional(readOnly = true)
    public List<FacMovimientoCajaModel> findAll() {
        return (List<FacMovimientoCajaModel>) datMovimientoCaja.findAll();
    }

    public FacMovimientoCajaModel save(FacMovimientoCajaModel fac_movimientocaja) throws SaveException {
        try {
            return datMovimientoCaja.save(fac_movimientocaja);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMovimientoCajaModel findOne(Long id) {
        return datMovimientoCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró movimiento caja con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacMovimientoCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la MovimientoCaja con ID " + id));
        // try {
        // datFacMovimientoCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacMovimientoCajaModel facMovimientoCajaModel = datMovimientoCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la MovimientoCaja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facMovimientoCajaModel.setGlbEstadoEstId(0L);

        try {
            datMovimientoCaja.save(facMovimientoCajaModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMovimientoCajaModel buscarPorId(Long mcaId) throws NotFoundException {
        Optional<FacMovimientoCajaModel> facMovimientocaja = Optional.ofNullable(datMovimientoCaja.findBymcaId(mcaId));
        if (facMovimientocaja.isEmpty()){
            throw new NotFoundException("No se movimiento caja con ID " + mcaId);
        }
        return facMovimientocaja.get();
    }

    public FacMovimientoCajaModel update(Long id, FacMovimientoCajaModel fac_MovimientoCaja) throws SaveException {
        datMovimientoCaja.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_MovimientoCaja.setMcaId(id);
        return datMovimientoCaja.save(fac_MovimientoCaja);
    }

    public FacMovimientoCajaModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datMovimientoCaja.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }

    public ArrayList<FacMovimientoCajaDTO> listarMovimientocaja(Long gecId, Long empId, LocalDate fechainicio, LocalDate fechafin){
        return datMovimientoCaja.getMovimientocaja(gecId, empId, fechainicio, fechafin);
    }
}
