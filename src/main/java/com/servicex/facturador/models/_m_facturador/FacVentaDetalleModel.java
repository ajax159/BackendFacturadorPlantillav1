package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
//import java.math.BigDecimal;

//import com.conexize.global.util.CapitalizeFirstLetter;
//import com.fasterxml.jackson.core.sym.Name;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
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
@Table(name = "fac_ventadetalle")
public class FacVentaDetalleModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ved_id")
    private Long vedId;

    @Column(name = "fac_venta_ven_id")
    private Long facVentavenid;

    @Column(name = "producto_id")
    private Long productoId;

    @NotEmpty
    @Column(name = "ved_cantidad")
    private String vedCantidad;

    @NotEmpty
    @Column(name = "ved_tipounidad")
    private String vedTipounidad;

    @Column(name = "ved_codigo")
    private String vedCodigo;

    @Column(name = "ved_tributo")
    private String vedTributo;

    @Column(name = "ved_montotributo")
    private String vedMontotributo;

    @Column(name = "ved_tipoafectaciontributo")
    private String vedTipoafectaciontributo;

    @NotEmpty
    @Column(name = "ved_valorunitario")
    private String vedValorunitario;

    @Column(name = "ved_valorunitariogratuito")
    private String vedValorunitariogratuito;

    @Column(name = "ved_preciounitarioitem")
    private String vedPreciounitarioitem;

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
