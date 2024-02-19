package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacVentaModel;
import com.servicex.facturador.repositories._m_facturador.FacVentaRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacVentaService {
    @Autowired
    private FacVentaRepository datFacVenta;

    @Transactional(readOnly = true)
    public List<FacVentaModel> findAll() {
        return (List<FacVentaModel>) datFacVenta.findAll();
    }

    public FacVentaModel save(FacVentaModel fac_venta) throws SaveException {
        try {
            return datFacVenta.save(fac_venta);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacVentaModel findOne(Long id) {
        return datFacVenta.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró venta con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacVentaModel facVentaModel = datFacVenta.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facVentaModel.setGlbEstadoEstId(0L);

        try {
            datFacVenta.save(facVentaModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacVentaModel buscarPorId(Long venId) throws NotFoundException {
        Optional<FacVentaModel> facVenta = Optional.ofNullable(datFacVenta.findByvenId(venId));//
        if (facVenta.isEmpty()){
            throw new NotFoundException("No se encontró venta con ID " + venId);
        }
        return facVenta.get();
    }

    public FacVentaModel update(Long id, FacVentaModel fac_Venta) throws SaveException {
        datFacVenta.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_Venta.setVenId(id);
        return datFacVenta.save(fac_Venta);
    }

    public FacVentaModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacVenta.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
