package com.servicex.facturador.models._m_facturador;
import java.io.Serializable;
import java.util.*;
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
@Table(name = "fac_roles")
public class FacRoles extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @NotEmpty
    @Column(name = "rol_descripcion")
    private String rolDescripcion;

    @Column(name = "rol_observacion")
    private String rolObservacion;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fac_roles_rol_id")
    private Set<FacPermisos> permisos;



}
