package com.servicex.facturador.services._m_facturador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicex.facturador.dtos.FacPermisosMenuDTO;
import com.servicex.facturador.repositories._m_facturador.FacPermisosRepository;

@Service
public class FacPermisosService {
    @Autowired
    private FacPermisosRepository datFacPermisos;

    public ArrayList<FacPermisosMenuDTO> getModulos(Long idRol) {
        return datFacPermisos.getModulos(idRol);
    }
}
