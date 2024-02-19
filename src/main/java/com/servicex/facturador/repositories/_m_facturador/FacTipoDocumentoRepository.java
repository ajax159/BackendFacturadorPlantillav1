package com.servicex.facturador.repositories._m_facturador;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacTipoDocumentoModel;
import java.util.*;
@Repository
public interface FacTipoDocumentoRepository extends JpaRepository<FacTipoDocumentoModel,Long>{
    FacTipoDocumentoModel findBytpdDescripcion(String tpdDescripcion);
    ArrayList<FacTipoDocumentoModel> findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
