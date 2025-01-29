package org.iesch.EjercicioMongo2.controller;

import org.iesch.EjercicioMongo2.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping("/persona/ciudad/{}")
    public ResponseEntity<?> personaPorCiudad(@PathVariable String ciudad){
        return ResponseEntity.ok(personaService.buscarCiudad(ciudad));
    }
}
