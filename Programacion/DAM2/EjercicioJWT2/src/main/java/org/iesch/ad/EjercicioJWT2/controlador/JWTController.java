package org.iesch.ad.EjercicioJWT2.controlador;

import org.iesch.ad.EjercicioJWT2.utilJWT.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/public")
    public String publicEndPoint(){
        return "Esto es un endpoint publico";
    }

    @GetMapping("/protected")
    public String protegidoEndPoint(){
        return "Esto es un endpoint privado";
    }
}
