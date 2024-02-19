package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
import java.math.BigDecimal;

//import com.conexize.global.util.CapitalizeFirstLetter;
import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import com.servicex.facturador.models.GlobalEntity;
//import org.springframework.format.annotation.DateTimeFormat;
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@Table(name = "fac_movimientodetalle")
public class FacMovimientoDetalleModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mde_id")
    private Long mdeId;

    @Column(name="fac_movimientocaja_mca_id")
    private Long facMovimientocajamcaId;

    @Column(name = "fac_cliente_cli_id")
    private Long facClienteCliId;

    @Column(name = "mde_tipomovimiento")
    private String mdeTipomovimiento;

    @Column(name = "mde_motivo")
    private Long mdeMotivo;

    @Column(name = "mde_descripcion")
    private String mdeDescripcion;

    @Column(name = "mde_observaciones")
    private String mdeObservaciones;

    @Column(name = "mde_monto")
    private BigDecimal mdeMonto;

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
