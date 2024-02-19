package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;

//import com.conexize.global.util.CapitalizeFirstLetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "fac_serie")
public class FacSerieModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ser_id")
    private Long serId;

    @Column(name = "fac_tipodocumentos_tpd_id")
    private Long facTipodocumentostpdid;

    @NotEmpty
    @Column(name = "ser_serie")
    private String serSerie;

    @NotNull 
    @Column(name = "ser_nactual")
    private Integer serNactual;

    @NotNull 
    @Column(name = "ser_ninicial")
    private Integer serNinicial;

    @NotNull 
    @Column(name = "ser_nfinal")
    private Integer serNfinal;

    @Column(name = "created_by")
    protected  Long createdBy;
    @Column(name = "updated_by")
    protected Long updatedBy;
    @Column(name = "gec_id")
    protected Long gecId;
    @Column(name = "emp_id")
    protected Long empId;
    @Column(name = "glb_estado_est_id", columnDefinition = "int default 1")
    protected  Long glbEstadoEstId;
}
