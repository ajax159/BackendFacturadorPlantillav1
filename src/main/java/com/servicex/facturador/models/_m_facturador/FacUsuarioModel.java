package com.servicex.facturador.models._m_facturador;

import java.io.Serializable;


//import com.conexize.global.util.CapitalizeFirstLetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;


import com.servicex.facturador.models.GlobalEntity;
//import org.springframework.format.annotation.DateTimeFormat;
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fac_usuario")
public class FacUsuarioModel extends GlobalEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long usuId;

    @NotEmpty
    @Column(name = "usu_nombrecompleto")
    private String usuNombrecompleto;

    @Column(name = "usu_correo")
    private String usuCorreo;

    @Column(name = "usu_usuario")
    private String usuUsuario;

    @Column(name = "usu_password")
    private String usuPassword;

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

    @ManyToOne
    @JoinColumn(name = "fac_roles_rol_id", nullable = false)
    private FacRoles facRoles;
    
    public String getRolNombre() {
        return facRoles.getRolDescripcion();
    }

    public Long getRoles() {
        return facRoles.getRolId(); //
    }


    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     List<GrantedAuthority> authorities = new ArrayList<>();
    //     String roleName = this.facRoles.getRolNombre();

    //     if(!roleName.startsWith("ROLE_")){
    //         roleName = "ROLE_" + roleName;
    //     }

    //     authorities.add(new SimpleGrantedAuthority(roleName));
    //     return null;
    // }

    // public Long getId() {
    //     return usuId;
    // }

    // @Override
    // public String getPassword() {
    //     return usuPassword;
    // }

    // @Override
    // public String getUsername() {
    //     return usuUsuario;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }
}
