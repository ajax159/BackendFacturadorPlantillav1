package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacVentaDetalleModel;
import com.servicex.facturador.repositories._m_facturador.FacVentaDetalleRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacVentaDetalleService {
    @Autowired
    private FacVentaDetalleRepository datFacVentaDetalle;

    @Transactional(readOnly = true)
    public List<FacVentaDetalleModel> findAll() {
        return (List<FacVentaDetalleModel>) datFacVentaDetalle.findAll();
    }

    public FacVentaDetalleModel save(FacVentaDetalleModel fac_ventadetalle) throws SaveException {
        try {
            return datFacVentaDetalle.save(fac_ventadetalle);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacVentaDetalleModel findOne(Long id) {
        return datFacVentaDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró ventadetalle con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacVentaDetalleModel facVentaDetalleModel = datFacVentaDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facVentaDetalleModel.setGlbEstadoEstId(0L);

        try {
            datFacVentaDetalle.save(facVentaDetalleModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacVentaDetalleModel buscarPorId(Long vedId) throws NotFoundException {
        Optional<FacVentaDetalleModel> facVentadetalle = Optional.ofNullable(datFacVentaDetalle.findByvedId(vedId));//
        if (facVentadetalle.isEmpty()){
            throw new NotFoundException("No se encontró ventadetalle con ID " + vedId);
        }
        return facVentadetalle.get();
    }

    public FacVentaDetalleModel update(Long id, FacVentaDetalleModel fac_VentaDetalle) throws SaveException {
        datFacVentaDetalle.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_VentaDetalle.setVedId(id);
        return datFacVentaDetalle.save(fac_VentaDetalle);
    }

    public FacVentaDetalleModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacVentaDetalle.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
