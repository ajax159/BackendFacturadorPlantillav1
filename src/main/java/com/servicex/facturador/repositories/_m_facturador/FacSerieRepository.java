package com.servicex.facturador.repositories._m_facturador;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.servicex.facturador.models._m_facturador.FacSerieModel;

@Repository
public interface FacSerieRepository extends JpaRepository<FacSerieModel,Long>{
    FacSerieModel findByserSerie(String serSerie);
    ArrayList<FacSerieModel> findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);
}
