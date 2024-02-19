package com.servicex.facturador.repositories._m_facturador;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacVentaModel;
// import java.util.List;
@Repository
public interface FacVentaRepository extends JpaRepository<FacVentaModel, Long>{
    FacVentaModel findByvenId(Long venId);
    FacVentaModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
