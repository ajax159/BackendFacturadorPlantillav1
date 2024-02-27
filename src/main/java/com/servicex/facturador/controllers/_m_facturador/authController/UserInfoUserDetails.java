package com.servicex.facturador.controllers._m_facturador.authController;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.servicex.facturador.models._m_facturador.FacUsuarioModel;

public class UserInfoUserDetails implements UserDetails{

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(FacUsuarioModel facUsuarioModel) {
        username=facUsuarioModel.getUsuUsuario();
        password=facUsuarioModel.getUsuPassword();
        authorities= Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + facUsuarioModel.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
