package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
import java.math.BigDecimal;
//import java.math.BigDecimal;
//import java.security.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.*;
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
@Table(name = "fac_venta")
public class FacVentaModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")
    private Long venId;

    @Column(name = "fac_tipodocumento_tpd_id")
    private Long facTipodocumentotpdid;

    @Column(name = "fac_caja_caj_id")
    private Long facCajacajid;

    @Column(name = "fac_cliente_cli_id")
    private Long facClientecliid;

    @Column(name = "ven_serie")
    private String venSerie;

    @Column(name = "ven_numero")
    private String venNumero;

    @NotEmpty
    @Column(name = "ven_horaemision")
    private String venHoraemision;

    @Column(name = "ven_fechavencimiento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate mcaFechacierre;

    @Column(name = "ven_moneda")
    private String venMoneda;

    @Column(name = "fac_mediopago_mpa_id")
    private Long facMediopagompaid;

    @Column(name = "fac_formapago_fpa_id")
    private Long facFormapagofpaid;

    @Column(name = "ven_cuotas")
    private String venCuotas;

    @Column(name = "ven_montocuota")
    private BigDecimal venMontocuota;

    @Column(name = "ven_motivo")
    private String venMotivo;

    @Column(name = "ven_totalventasgravadas")
    private String venTotalventasgravadas;

    @Column(name = "ven_totalgratuito")
    private String venTotalgratuito;

    @Column(name = "ven_observacion")
    private String venObservacion;

    @Column(name = "ven_igv")
    private String venIgv;

    @Column(name = "ven_importetotal")
    private String venImportetotal;

    @Column(name = "ven_hash")
    private String venHash;

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
