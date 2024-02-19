package com.servicex.facturador.repositories._m_facturador;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacCajaModel;
// import java.util.List;
@Repository
public interface FacCajaRepository extends JpaRepository<FacCajaModel,Long>{
    FacCajaModel findBycajDescripcion(String cajDescripcion);
    ArrayList<FacCajaModel> findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
