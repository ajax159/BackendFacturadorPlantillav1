package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacPermisos;

@Repository
public interface FacPermisosRepository extends JpaRepository<FacPermisos,Long>{
    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerRead(Long facMenuMenId, Long facRolesRolId, int perRead);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerInsert(Long facMenuMenId, Long facRolesRolId, int perInsert);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerUpdate(Long facMenuMenId, Long facRolesRolId, int perUpdate);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerDelete(Long facMenuMenId, Long facRolesRolId, int perDelete);
}
