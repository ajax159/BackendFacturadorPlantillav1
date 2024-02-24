package com.servicex.facturador.controllers._m_facturador.authController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtTokenProvider.generateToken(loginRequest.getUsername());
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
