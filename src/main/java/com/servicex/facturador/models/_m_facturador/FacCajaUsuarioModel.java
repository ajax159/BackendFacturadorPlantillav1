package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;

// import com.conexize.global.util.CapitalizeFirstLetter;
import jakarta.persistence.*;
// import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import com.servicex.facturador.models.GlobalEntity;
// import org.springframework.format.annotation.DateTimeFormat;
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@Table(name = "fac_cajausuario")
public class FacCajaUsuarioModel extends GlobalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cju_id")
    private Long cjuId;

    @Column(name = "fac_usuario_usu_id")
    private Long facUsuarioUsuId;
    @Column(name = "fac_caja_caj_id")
    private Long facCajaCajId;
    @Column(name = "glb_estado_est_id", columnDefinition = "int default 1")
    protected  Long glbEstadoEstId;
    @Column(name = "created_by")
    protected  Long createdBy;
    @Column(name = "updated_by")
    protected Long updatedBy;
    @Column(name = "gec_id")
    protected Long gecId;
    @Column(name = "emp_id")
    protected Long empId;
}
