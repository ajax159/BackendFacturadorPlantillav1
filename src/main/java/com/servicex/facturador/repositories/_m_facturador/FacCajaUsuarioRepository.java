package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.dtos.FacCajaUsuarioDTO;
import com.servicex.facturador.dtos.FacCajaUsuarioNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaUsuarioModel;
import java.util.*;
@Repository
public interface FacCajaUsuarioRepository extends JpaRepository<FacCajaUsuarioModel,Long>{
    @Query("SELECT COUNT(c) > 0 FROM FacCajaUsuarioModel c WHERE c.facUsuarioUsuId = ?1 AND c.facCajaCajId = ?2 AND c.gecId = ?3 AND c.empId = ?4")
    boolean existsByFacUsuarioUsuIdAndFacCajaCajIdAndGecIdAndEmpId(Long facUsuarioUsuId, Long facCajaCajId, Long gecId, Long empId);

    FacCajaUsuarioModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);

    @Query("SELECT new com.servicex.facturador.dtos.FacCajaUsuarioDTO(" +
    "cu.cjuId, cu.facCajaCajId, cu.facUsuarioUsuId, u.usuId, u.usuNombrecompleto) " +
    "FROM FacCajaUsuarioModel cu " +
    "LEFT JOIN FacUsuarioModel u ON u.usuId = cu.facUsuarioUsuId " +
    "where cu.facCajaCajId = :idcaja ")
    ArrayList<FacCajaUsuarioDTO> getIdCaja(@Param("idcaja") Long idcaja);

// SELECT DISTINCT u.`usu_id`
// FROM fac_usuario u
// LEFT JOIN fac_cajausuario cd ON u.`usu_id` = cd.`fac_usuario_usu_id`
// WHERE cd.`fac_usuario_usu_id` IS NULL 
// OR (cd.`fac_caja_caj_id` <> 4 AND cd.`fac_usuario_usu_id` NOT IN (SELECT `fac_usuario_usu_id` FROM fac_usuario WHERE `fac_caja_caj_id` = 4))
    @Query("SELECT DISTINCT new com.servicex.facturador.dtos.FacCajaUsuarioNotDTO(" +
    "u.usuId, u.usuNombrecompleto) " +
    "FROM FacUsuarioModel u " +
    "LEFT JOIN FacCajaUsuarioModel cu ON u.usuId = cu.facUsuarioUsuId " +
    "WHERE cu.facUsuarioUsuId IS NULL " +
    "OR (cu.facCajaCajId <> :idcaja AND cu.facUsuarioUsuId NOT IN(" +
    "SELECT facUsuarioUsuId FROM FacCajaUsuarioModel WHERE facCajaCajId = :idcaja))")
    ArrayList<FacCajaUsuarioNotDTO> getNotIdCaja(@Param("idcaja") Long idcaja);

    @Query("SELECT new com.servicex.facturador.dtos.FacCajaUsuarioDTO(" +
    "cu.cjuId, cu.facCajaCajId, cu.facUsuarioUsuId, u.usuId, u.usuNombrecompleto) " +
    "FROM FacCajaUsuarioModel cu " +
    "JOIN FacUsuarioModel u ON u.usuId = cu.facUsuarioUsuId " +
    "WHERE u.usuNombrecompleto LIKE %:nombre% ")
    ArrayList<FacCajaUsuarioDTO> getUsuarioporNombre(@Param("nombre") String nombre);
}
