package com.servicex.facturador.controllers._m_facturador.authController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class cryp {
        public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "venta";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
