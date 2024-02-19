package com.servicex.facturador.repositories._m_facturador;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacMedioPagoModel;
// import java.util.List;
@Repository
public interface FacMedioPagoRepository extends JpaRepository<FacMedioPagoModel, Long> {
    FacMedioPagoModel findBympaDescripcion(String mpaDescripcion);
    FacMedioPagoModel findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
