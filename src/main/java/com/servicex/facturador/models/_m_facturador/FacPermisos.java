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
@Table(name = "fac_permisos")
public class FacPermisos extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long perId;

    @NotEmpty
    @Column(name = "per_read")
    private int perRead;

    @NotEmpty
    @Column(name = "per_insert")
    private int perInsert;

    @NotEmpty
    @Column(name = "per_update")
    private int perUpdate;

    @NotEmpty
    @Column(name = "per_delete")
    private int perDelete;

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

    @ManyToOne
    @JoinColumn(name = "fac_menu_men_id", nullable = false)
    private FacMenu facMenu;

    @ManyToOne
    @JoinColumn(name = "fac_roles_rol_id", nullable = false)
    private FacRoles facRoles;
}
