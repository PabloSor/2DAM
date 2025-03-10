package org.iesch.ad.Ej2.controller;

import org.iesch.ad.Ej2.model.Persona;
import org.iesch.ad.Ej2.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(personaService.encontrarTodos());
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<?> buscarId(@PathVariable String id){
        return ResponseEntity.ok(personaService.buscarId(id));
    }

    @PostMapping("/personas")
    public ResponseEntity<?> crearNuevo(@RequestBody Persona persona){
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable String id, @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.actualizarPersona(id, persona));
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String id){
        return ResponseEntity.ok(personaService.borrarPersona(id));
    }

    @GetMapping("/vehiculos/{matricula}/potencia-fiscal")
    public ResponseEntity<?> potenciaVehiculo(@PathVariable String matricula){
        return ResponseEntity.ok(personaService.potenciaVehiculo(matricula));
    }
}
