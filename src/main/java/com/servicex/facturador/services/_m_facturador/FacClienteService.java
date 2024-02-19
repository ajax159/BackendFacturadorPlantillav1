package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacClienteModel;
import com.servicex.facturador.repositories._m_facturador.FacClienteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class FacClienteService {
    @Autowired
    private FacClienteRepository datFacCliente;

    @Transactional(readOnly = true)
    public List<FacClienteModel> findAll() {
        return (List<FacClienteModel>) datFacCliente.findAll();
    }

    public FacClienteModel save(FacClienteModel fac_cliente) throws SaveException {
        try {
            return datFacCliente.save(fac_cliente);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacClienteModel findOne(Long id) {
        return datFacCliente.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró clase documento con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCliente.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la Cliente con ID " + id));
        // try {
        // datFacCliente.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacClienteModel facClienteModel = datFacCliente.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la Cliente con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facClienteModel.setGlbEstadoEstId(0L);

        try {
            datFacCliente.save(facClienteModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacClienteModel buscarPorId(String cliNdocumento) throws NotFoundException {
        Optional<FacClienteModel> facclienteId = Optional.ofNullable(datFacCliente.findBycliNdocumento(cliNdocumento));
        if (facclienteId.isEmpty()){
            throw new NotFoundException("No se encontró el documento con ID " + cliNdocumento);
        }
        return facclienteId.get();
    }

    public FacClienteModel update(Long id, FacClienteModel fac_cliente) throws SaveException {
        datFacCliente.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_cliente.setCliId(id);
        return datFacCliente.save(fac_cliente);
    }

    public FacClienteModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacCliente.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }
}
