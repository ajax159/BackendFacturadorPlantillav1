package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.servicex.facturador.dtos.FacCajaSerieDto;
import com.servicex.facturador.dtos.FacCajaSerieNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaSerieModel;

import java.util.*;
@Repository
public interface FacCajaSerieRepository extends JpaRepository<FacCajaSerieModel,Long>{
    @Query("SELECT COUNT(c) > 0 FROM FacCajaSerieModel c WHERE c.facSerieSerId = ?1 AND c.facCajaCajId = ?2 AND c.gecId = ?3 AND c.empId = ?4")
    boolean existsByfacSerieSerIdAndFacCajaCajIdAndGecIdAndEmpId(Long facSerieSerId, Long facCajaCajId, Long gecId, Long empId);
    FacCajaSerieModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);

    @Query("SELECT new com.servicex.facturador.dtos.FacCajaSerieDto(" +
    "cd.cjsId, cd.facCajaCajId, u.serId, u.facTipodocumentostpdid, u.serSerie) " +
    "FROM FacCajaSerieModel cd " +
    "JOIN FacSerieModel u ON u.serId = cd.facSerieSerId " +
    "where cd.facCajaCajId = :idcaja ")
    ArrayList<FacCajaSerieDto> getIdCaja(@Param("idcaja") Long idcaja);

    @Query("SELECT DISTINCT new com.servicex.facturador.dtos.FacCajaSerieNotDTO(" +
    "u.serId, u.serSerie) " +
    "FROM FacSerieModel u " +
    "LEFT JOIN FacCajaSerieModel cd ON u.serId = cd.facSerieSerId " +
    "where cd.facSerieSerId IS NULL " +
    "OR (cd.facCajaCajId <> :idcaja AND cd.facSerieSerId NOT IN(" +
    "SELECT facSerieSerId FROM FacCajaSerieModel WHERE facCajaCajId = :idcaja) " +
    "AND u.facTipodocumentostpdid IN(SELECT facTipoDocumentoTpdId FROM FacCajaDocumentoModel WHERE facCajaCajId = :idcaja))")
    ArrayList<FacCajaSerieNotDTO> getnotIdCaja(@Param("idcaja") Long idcaja);
}
