package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import com.servicex.facturador.models.GlobalEntity;
import com.servicex.facturador.util.CapitalizeFirstLetter;
//import org.springframework.format.annotation.DateTimeFormat;
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@Table(name = "fac_tipodocumento")
public class FacTipoDocumentoModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpd_id")
    private Long tpdId;

    @NotEmpty
    @CapitalizeFirstLetter
    @Column(name = "tpd_descripcion")
    private String tpdDescripcion;

    @NotEmpty
    @Column(name = "fac_clasedocumento_cld_id")
    private Long facClasedocumentocldid;

    @NotEmpty
    @Column(name = "tpd_sigla")
    private String tpdSigla;

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
