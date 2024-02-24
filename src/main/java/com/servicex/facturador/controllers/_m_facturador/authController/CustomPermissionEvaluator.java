package com.servicex.facturador.controllers._m_facturador.authController;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.servicex.facturador.models._m_facturador.FacPermisos;
import com.servicex.facturador.repositories._m_facturador.FacPermisosRepository;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private FacPermisosRepository permisosRepository;

    @Override
    public boolean hasPermission(Authentication auth, Object targetMenuId, Object permission) {
        Long menuId = Long.valueOf((String) targetMenuId);
        Long roleId = getRoleIdFromAuthentication(auth);
        String perm = (String) permission;

        FacPermisos permiso;
        switch (perm) {
            case "read":
                permiso = permisosRepository.findByFacMenuMenIdAndFacRolesRolIdAndPerRead(menuId, roleId, 1);
                break;
            case "insert":
                permiso = permisosRepository.findByFacMenuMenIdAndFacRolesRolIdAndPerInsert(menuId, roleId, 1);
                break;
            case "update":
                permiso = permisosRepository.findByFacMenuMenIdAndFacRolesRolIdAndPerUpdate(menuId, roleId, 1);
                break;
            case "delete":
                permiso = permisosRepository.findByFacMenuMenIdAndFacRolesRolIdAndPerDelete(menuId, roleId, 1);
                break;
            default:
                return false;
        }

        return permiso != null;
    }

    private Long getRoleIdFromAuthentication(Authentication auth) {
        return auth.getAuthorities().stream()
            .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith("ROLE_"))
            .map(grantedAuthority -> Long.valueOf(grantedAuthority.getAuthority().substring(5))) // Assumes the role ID is after "ROLE_"
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException("hasPermission not supported for id and type");
    }
}
