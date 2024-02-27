package com.servicex.facturador.repositories._m_facturador;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.servicex.facturador.dtos.FacPermisosMenuDTO;
import com.servicex.facturador.models._m_facturador.FacPermisos;

@Repository
public interface FacPermisosRepository extends JpaRepository<FacPermisos,Long>{
    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerRead(Long facMenuMenId, Long facRolesRolId, int perRead);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerInsert(Long facMenuMenId, Long facRolesRolId, int perInsert);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerUpdate(Long facMenuMenId, Long facRolesRolId, int perUpdate);

    FacPermisos findByFacMenuMenIdAndFacRolesRolIdAndPerDelete(Long facMenuMenId, Long facRolesRolId, int perDelete);

    // SELECT per.`fac_menu_men_id` AS MENU, mo.`mod_id` AS MODULO
    // FROM fac_permisos AS per
    // LEFT JOIN fac_menu me ON per.`fac_menu_men_id` = me.`men_id`
    // LEFT JOIN fac_modulo mo ON me.`fac_modulo_mod_id` = mo.`mod_id`
    // WHERE per.`fac_roles_rol_id` = 1;
    @Query("SELECT new com.servicex.facturador.dtos.FacPermisosMenuDTO(per.facMenu.menId, mo.modId) " +
    "FROM FacPermisos per " +
    "LEFT JOIN per.facMenu me " +
    "LEFT JOIN FacModulo mo ON me.facmodulomodId = mo.modId " +
    "WHERE per.facRoles.rolId = :idrol")
    ArrayList<FacPermisosMenuDTO> getModulos(@Param("idrol") Long idRol);
}
