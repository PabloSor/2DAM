package org.iesch.ad.DemoFiltros_ModelosUsuario.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class ZonaAdmin {

    @GetMapping("/saludo")
    public ResponseEntity<?> saludo(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("Saludo: ", "Hola zona Administrador<.");

        return ResponseEntity.ok(hashMap);
    }
}
