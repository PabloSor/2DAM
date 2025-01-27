package org.iesch.ad.DemoFiltros_ModelosUsuario.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/public")
public class ZonaPublica {

    @GetMapping("/saludo")
    public ResponseEntity<?> saludo(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("Saludo: ", "Hola zona Publica.");

        return ResponseEntity.ok(hashMap);
    }
}
