package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacMovimientoDetalleModel;
import com.servicex.facturador.repositories._m_facturador.FacMovimientoDetalleRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacMovimientoDetalleService {
    @Autowired
    private FacMovimientoDetalleRepository datMovimientoDetalle;

    @Transactional(readOnly = true)
    public List<FacMovimientoDetalleModel> findAll() {
        return (List<FacMovimientoDetalleModel>) datMovimientoDetalle.findAll();
    }

    public FacMovimientoDetalleModel save(FacMovimientoDetalleModel fac_movimientodetalle) throws SaveException {
        try {
            return datMovimientoDetalle.save(fac_movimientodetalle);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMovimientoDetalleModel findOne(Long id) {
        return datMovimientoDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró movimiento detalle con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacMovimientoDetalle.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la MovimientoDetalle con ID " + id));
        // try {
        // datFacMovimientoDetalle.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacMovimientoDetalleModel facMovimientoDetalleModel = datMovimientoDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la MovimientoDetalle con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facMovimientoDetalleModel.setGlbEstadoEstId(0L);

        try {
            datMovimientoDetalle.save(facMovimientoDetalleModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacMovimientoDetalleModel buscarPorId(Long mdeId) throws NotFoundException {
        Optional<FacMovimientoDetalleModel> facMovimientodetalle = Optional.ofNullable(datMovimientoDetalle.findBymdeId(mdeId));
        if (facMovimientodetalle.isEmpty()){
            throw new NotFoundException("No se movimiento detalle con ID " + mdeId);
        }
        return facMovimientodetalle.get();
    }

    public FacMovimientoDetalleModel update(Long id, FacMovimientoDetalleModel fac_MovimientoDetalle) throws SaveException {
        datMovimientoDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_MovimientoDetalle.setMdeId(id);
        return datMovimientoDetalle.save(fac_MovimientoDetalle);
    }

    public FacMovimientoDetalleModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datMovimientoDetalle.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
