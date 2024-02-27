package com.servicex.facturador.models._m_facturador;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import com.servicex.facturador.models.GlobalEntity;

@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@Table(name = "fac_modulo")
public class FacModulo extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mod_id")
    private Long modId;

    @Column(name = "mod_descripcion")
    private String modDescripcion;

    @NotEmpty
    @Column(name = "created_by")
    protected  Long createdBy;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @NotEmpty
    @Column(name = "gec_id")
    private Long gecId;

    @NotEmpty
    @Column(name = "emp_id")
    private Long empId;

    @NotEmpty
    @Column(name = "glb_estado_est_id", columnDefinition = "int default 1")
    protected  Long glbEstadoEstId;
}
