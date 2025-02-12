package org.iesch.ad.Ejercicios_Thymeleaf_Spring.controller;

import org.iesch.ad.Ejercicios_Thymeleaf_Spring.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehiculoController {

    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/vehiculos")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(vehiculoService.getAll());
    }
}
