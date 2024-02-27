package com.servicex.facturador.controllers._m_facturador.authController;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.servicex.facturador.models._m_facturador.FacUsuarioModel;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userInfoUserDetailsService")
    private UserInfoUserDetailsService userService;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            FacUsuarioModel user = userService.loadFacUsuarioByUsername(loginRequest.getUsername());
            return jwtTokenProvider.generateToken(user);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    // public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
    //     try {
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(
    //                         loginRequest.getUsername(),
    //                         loginRequest.getPassword()
    //                 )
    //         );

    //         System.out.println("Authentication: " + authentication);
    //         SecurityContextHolder.getContext().setAuthentication(authentication);
    //         String jwt = jwtTokenProvider.generateToken(authentication);
    //         System.out.println("jwt: " + jwt);
    //         return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    //     } catch (Exception e) {
    //         System.out.println("Error during authentication: " + e);
    //         throw e;
    //     }
    // }
}
