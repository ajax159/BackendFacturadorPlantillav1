package com.servicex.facturador.services._m_facturador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
//import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaUsuarioDTO;
import com.servicex.facturador.dtos.FacCajaUsuarioNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaUsuarioModel;
import com.servicex.facturador.repositories._m_facturador.FacCajaUsuarioRepository;
import java.util.*;
@Service
public class FacCajaUsuarioService {
    @Autowired
    private FacCajaUsuarioRepository datFacCajaUsuario;

    @Transactional(readOnly = true)
    public List<FacCajaUsuarioModel> findAll() {
        return (List<FacCajaUsuarioModel>) datFacCajaUsuario.findAll();
    }

    public FacCajaUsuarioModel save(FacCajaUsuarioModel fac_cajausuario) throws SaveException {
        try {
            return datFacCajaUsuario.save(fac_cajausuario);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacCajaUsuarioModel findOne(Long id) {
        return datFacCajaUsuario.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró caja usuario con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        datFacCajaUsuario.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró la caja usuario con ID " + id));
        try {
            datFacCajaUsuario.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    // @Transactional(readOnly = true)
    // public FacCajaUsuarioModel buscarPorIdUsuario(Long facUsuarioUsuId) throws NotFoundException {
    //     Optional<FacCajaUsuarioModel> facCajausuario = Optional.ofNullable(datFacCajaUsuario.findByfacUsuarioUsuId(facUsuarioUsuId));
    //     if (facCajausuario.isEmpty()){
    //         throw new NotFoundException("No se encontró el documento con ID " + facUsuarioUsuId);
    //     }
    //     return facCajausuario.get();
    // }

    public FacCajaUsuarioModel listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacCajaUsuario.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }

    public ArrayList<FacCajaUsuarioDTO> getIdCaja(Long idcaja){
        return datFacCajaUsuario.getIdCaja(idcaja);
    }
    public ArrayList<FacCajaUsuarioNotDTO> getNotIdCaja(Long idcaja){
        return datFacCajaUsuario.getNotIdCaja(idcaja);
    }
    public ArrayList<FacCajaUsuarioDTO> getUsuarioporNombre(String nombre){
        return datFacCajaUsuario.getUsuarioporNombre(nombre);
    }
}
