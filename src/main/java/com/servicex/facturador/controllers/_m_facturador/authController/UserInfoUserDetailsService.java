package com.servicex.facturador.controllers._m_facturador.authController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.servicex.facturador.models._m_facturador.FacUsuarioModel;
import com.servicex.facturador.repositories._m_facturador.FacUsuarioRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private FacUsuarioRepository datFacUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FacUsuarioModel> usuario = datFacUsuario.findByUsuUsuario(username);
        return usuario.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));
    }

    public FacUsuarioModel loadFacUsuarioByUsername(String username) {
        return datFacUsuario.findByUsuUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));
    }
}
