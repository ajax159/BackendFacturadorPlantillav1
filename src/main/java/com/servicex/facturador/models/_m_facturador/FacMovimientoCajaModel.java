package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
//import com.conexize.global.util.CapitalizeFirstLetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import com.servicex.facturador.models.GlobalEntity;
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@Table(name = "fac_movimientocaja")
public class FacMovimientoCajaModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mca_id")
    private Long mcaId;

    @Column(name = "fac_caja_caj_id")
    private Long faccajacajId;

    @Column(name = "mca_tipomovimiento")
    private Long mcaTipomovimiento;

    @NotNull
    @Column(name = "mca_fechaapertura")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate mcaFechaapertura;

    @Column(name = "mca_fechacierre")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate mcaFechacierre;

    @Column(name = "mca_fechareajuste")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate mcaFechareajuste;

    @Column(name = "mca_totalingresos")
    private BigDecimal mcaTotalingresos;

    @Column(name = "mca_anuladoingresos")
    private BigDecimal mcaAnuladoingresos;

    @Column(name = "mca_totalegresos")
    private BigDecimal mcaTotalegresos;

    @Column(name = "mca_anuladoegresos")
    private BigDecimal mcaAnuladoegresos;

    @Column(name = "mca_total")
    private BigDecimal mcaTotal;

    @Column(name = "mca_montoreal")
    private BigDecimal mcaMontoreal;

    @Column(name = "mca_montodiferencia")
    private BigDecimal mcaMontodiferencia;

    @Column(name = "mca_moneda")
    private Long mcaMoneda;

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
