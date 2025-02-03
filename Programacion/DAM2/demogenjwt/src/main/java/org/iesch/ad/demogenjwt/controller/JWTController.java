package org.iesch.ad.demogenjwt.controller;

import org.iesch.ad.demogenjwt.utilJWT.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/generar")
    public String generarToken(@RequestParam String username){
        return jwtUtil.generatedToken(username);
    }
}
