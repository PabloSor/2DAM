package org.ieach.ad.basicosJWT.controlador;

import io.jsonwebtoken.Claims;
import org.ieach.ad.basicosJWT.utilJWT.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/ej1")
    public ResponseEntity<?> getClaim(@RequestParam String token){
        return ResponseEntity.ok(jwtUtil.extractAllClaims(token));
    }

    @GetMapping("/ej2")
    public ResponseEntity<?> validateToken(@RequestParam String token){
        String username = jwtUtil.extractUserName(token);

        return ResponseEntity.ok(jwtUtil.validateToken(token, username));
    }

    @GetMapping("/ej3")
    public ResponseEntity<?> extraerClaim(@RequestParam String token, @RequestParam String claimAExtraer){
        Claims claims = jwtUtil.extractAllClaims(token);
        // Faltaría añadir lógica para comprobar que debo devolver y demas...

        return ResponseEntity.ok(claims.get(claimAExtraer, String.class));
    }

    @GetMapping("/ej5")
    public ResponseEntity<?> fechaExpira(@RequestParam String token){

        return ResponseEntity.ok(jwtUtil.extractExpiration(token));
    }

    @GetMapping("/ej6")
    public ResponseEntity<?> generaConMas(@RequestParam String username){

        return ResponseEntity.ok(jwtUtil.generateMyToken(username));
    }
}
