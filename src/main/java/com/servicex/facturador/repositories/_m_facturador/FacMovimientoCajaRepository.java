package com.servicex.facturador.repositories._m_facturador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.servicex.facturador.dtos.FacMovimientoCajaDTO;
import com.servicex.facturador.models._m_facturador.FacMovimientoCajaModel;
import java.time.LocalDate;
import java.util.*;
@Repository
public interface FacMovimientoCajaRepository extends JpaRepository<FacMovimientoCajaModel, Long>{
    FacMovimientoCajaModel findBymcaId(Long mcaId);
    FacMovimientoCajaModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);

//     SELECT mc.`mca_fechaapertura`,mc.mca_fechacierre,mc.`emp_id`, c.`caj_descripcion`, u.`usu_nombrecompleto`, mc.`mca_moneda`, mc.glb_estado_est_id,
// CASE
// 	WHEN mc.`mca_moneda` = 'S' THEN 'SOLES'
// 	WHEN mc.`mca_moneda` = 'D' THEN 'DOLARES'
// END AS moneda,
// CASE
// 	WHEN mc.glb_estado_est_id = 1 THEN 'ABIERTO'
// 	WHEN mc.glb_estado_est_id = 3 THEN 'CERRADO'
// END AS estado
// FROM fac_movimientocaja mc
// LEFT JOIN fac_usuario u ON mc.`created_by` = u.`usu_id`
// LEFT JOIN fac_caja c ON mc.`fac_caja_caj_id` = c.`caj_id`
// WHERE mc.gec_id = 1 AND mc.emp_id = 1 AND mc.glb_estado_est_id = 1
@Query("SELECT new com.servicex.facturador.dtos.FacMovimientoCajaDTO(" +
    "mc.mcaId, mc.mcaFechaapertura, mc.mcaFechacierre, mc.empId, c.cajDescripcion, u.usuNombrecompleto, mc.mcaMoneda, mc.glbEstadoEstId, " +
    "CASE mc.mcaMoneda " +
    "   WHEN 1 THEN 'Soles' " +
    "   WHEN 2 THEN 'Dólares' " +
    "END AS moneda, " +
    "CASE mc.glbEstadoEstId " +
    "   WHEN 1 THEN 'ABIERTO' " +
    "   WHEN 3 THEN 'CERRADO' " +
    "END AS estado) " +
    "FROM FacMovimientoCajaModel mc " +
    "LEFT JOIN FacUsuarioModel u ON mc.createdBy = u.usuId " +
    "LEFT JOIN FacCajaModel c ON mc.faccajacajId = c.cajId " +
    "WHERE mc.gecId = :idgec AND mc.empId = :idemp AND mc.glbEstadoEstId = 1 AND mc.mcaFechaapertura BETWEEN :fechainicio AND :fechafin")
    ArrayList<FacMovimientoCajaDTO> getMovimientocaja(@Param("idgec") Long gecId,@Param("idemp") Long empId,@Param("fechainicio") LocalDate fechainicio,@Param("fechafin") LocalDate fechafin);

    //select * from fac_movimientocaja where fac_caja_caj_id = 4 and created_by = 1 and DATE(mca_fechaapertura) = '2024-01-04'
    @Query("SELECT COUNT(c) > 0 FROM FacMovimientoCajaModel c WHERE c.faccajacajId = ?1 AND c.createdBy = ?2 AND DATE(c.mcaFechaapertura) = ?3")
    boolean existsByFacCajaCajIdAndCreatedByAndMcaFechaapertura(Long faccajacajId, Long createdBy, LocalDate mcaFechaapertura);
}
