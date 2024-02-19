package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.servicex.facturador.dtos.FacCajaDocumentoDTO;
import com.servicex.facturador.dtos.FacCajaDocumentoNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaDocumentoModel;

import java.util.*;
@Repository
public interface FacCajaDocumentoRepository extends JpaRepository<FacCajaDocumentoModel,Long>{
    @Query("SELECT COUNT(c) > 0 FROM FacCajaDocumentoModel c WHERE c.facTipoDocumentoTpdId = ?1 AND c.facCajaCajId = ?2 AND c.gecId = ?3 AND c.empId = ?4")
    boolean existsByfacTipoDocumentoTpdIdAndFacCajaCajIdAndGecIdAndEmpId(Long facTipoDocumentoTpdId, Long facCajaCajId, Long gecId, Long empId);
    FacCajaDocumentoModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);

    @Query("SELECT new com.servicex.facturador.dtos.FacCajaDocumentoDTO(" +
    "cd.cjdId, cd.facCajaCajId, u.tpdId, u.tpdDescripcion, u.facClasedocumentocldid) " +
    "FROM FacCajaDocumentoModel cd " +
    "JOIN FacTipoDocumentoModel u ON u.tpdId = cd.facTipoDocumentoTpdId " +
    "where cd.facCajaCajId = :idcaja ")
    ArrayList<FacCajaDocumentoDTO> getIdCaja(@Param("idcaja") Long idcaja);

// SELECT DISTINCT cd.`fac_tipodocumento_tpd_id`
// FROM fac_cajadocumento cd
// LEFT JOIN fac_tipodocumento u ON u.`tpd_id` = cd.`fac_tipodocumento_tpd_id`
// WHERE cd.`fac_tipodocumento_tpd_id` IS NULL
// OR (cd.`fac_caja_caj_id` <> 6 AND cd.`fac_tipodocumento_tpd_id` NOT IN (SELECT `fac_tipodocumento_tpd_id` FROM fac_cajadocumento WHERE `fac_caja_caj_id` = 6))
    @Query("SELECT DISTINCT new com.servicex.facturador.dtos.FacCajaDocumentoNotDTO(" +
    "u.tpdId, u.tpdDescripcion) " +
    "FROM FacTipoDocumentoModel u " +
    "LEFT JOIN FacCajaDocumentoModel cd ON u.tpdId = cd.facTipoDocumentoTpdId " +
    "where cd.facTipoDocumentoTpdId IS NULL " +
    "OR (cd.facCajaCajId <> :idcaja AND cd.facTipoDocumentoTpdId NOT IN(" +
    "SELECT facTipoDocumentoTpdId FROM FacCajaDocumentoModel WHERE facCajaCajId = :idcaja))")
    ArrayList<FacCajaDocumentoNotDTO> getnotIdCaja(@Param("idcaja") Long idcaja);
}
