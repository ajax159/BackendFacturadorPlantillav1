package com.servicex.facturador.repositories._m_facturador;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.servicex.facturador.models._m_facturador.FacUsuarioModel;
// import java.util.List;
@Repository
public interface FacUsuarioRepository extends JpaRepository<FacUsuarioModel, Long>{
    FacUsuarioModel findByusuNombrecompleto(String usuNombrecompleto);
    ArrayList<FacUsuarioModel> findByGecIdAndEmpIdAndGlbEstadoEstIdNot(Long gecId, Long empId, Long glbEstadoEstId);

    Optional<FacUsuarioModel> findByUsuUsuario(String username);
}
