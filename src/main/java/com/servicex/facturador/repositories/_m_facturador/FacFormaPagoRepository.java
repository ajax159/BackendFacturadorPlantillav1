package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacFormaPagoModel;
// import java.util.List;
@Repository
public interface FacFormaPagoRepository extends JpaRepository<FacFormaPagoModel,Long>{
    FacFormaPagoModel findByfpaDescripcion(String fpaDescripcion);
    FacFormaPagoModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
