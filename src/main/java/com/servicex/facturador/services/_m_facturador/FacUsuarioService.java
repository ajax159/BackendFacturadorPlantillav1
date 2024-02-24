package com.servicex.facturador.services._m_facturador;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.controllers._m_facturador.authController.UserInfoUserDetails;
import com.servicex.facturador.models._m_facturador.FacUsuarioModel;
import com.servicex.facturador.repositories._m_facturador.FacUsuarioRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacUsuarioService{
    @Autowired
    private FacUsuarioRepository datFacUsuario;

    @Transactional(readOnly = true)
    public List<FacUsuarioModel> findAll() {
        return (List<FacUsuarioModel>) datFacUsuario.findAll();
    }

    public FacUsuarioModel save(FacUsuarioModel fac_usuario) throws SaveException {
        try {
            return datFacUsuario.save(fac_usuario);
        } catch (Exception e) {
            throw new SaveException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacUsuarioModel findOne(Long id) {
        return datFacUsuario.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró usuario con ID " + id));
    }

    public void delete(Long id) throws DeleteException {
        // datFacCaja.findById(id).orElseThrow(() -> new EntityNotFoundException("No se
        // encontró la caja con ID " + id));
        // try {
        // datFacCaja.deleteById(id);
        // } catch (Exception e) {
        // throw new DeleteException(e);
        // }
        FacUsuarioModel facUsuarioModel = datFacUsuario.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la caja con ID " + id));

        // Establece glbEstadoEstId en 0 en lugar de eliminar
        facUsuarioModel.setGlbEstadoEstId(0L);

        try {
            datFacUsuario.save(facUsuarioModel); // Guarda la entidad actualizada
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Transactional(readOnly = true)
    public FacUsuarioModel buscarPorId(String usuNombrecompleto) throws NotFoundException {
        Optional<FacUsuarioModel> facUsuario = Optional.ofNullable(datFacUsuario.findByusuNombrecompleto(usuNombrecompleto));//
        if (facUsuario.isEmpty()){
            throw new NotFoundException("No se encontró usuario con ID " + usuNombrecompleto);
        }
        return facUsuario.get();
    }

    public FacUsuarioModel update(Long id, FacUsuarioModel fac_Usuario) throws SaveException {
        datFacUsuario.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con ID " + id));
        fac_Usuario.setUsuId(id);
        return datFacUsuario.save(fac_Usuario);
    }

    public ArrayList<FacUsuarioModel> listarPorGecIdEmpIdYEstado(Long gecId, Long empId, Long glbEstadoEstId) {
        return datFacUsuario.findByGecIdAndEmpIdAndGlbEstadoEstIdNot(gecId, empId, 0L);
    }



}
