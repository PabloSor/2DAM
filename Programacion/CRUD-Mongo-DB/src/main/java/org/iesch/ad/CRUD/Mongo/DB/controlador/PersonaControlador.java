package org.iesch.ad.CRUD.Mongo.DB.controlador;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.iesch.ad.CRUD.Mongo.DB.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaControlador {

    @Autowired
    PersonaServicio personaServicio;

    @GetMapping("/persona")
    public ResponseEntity<?> getTodos(){
        List<Persona> listaPersonas = personaServicio.getTodos();
        if (listaPersonas.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaPersonas);
    }

    @PostMapping("/persona")
    public ResponseEntity<?> añadirPersona(@RequestBody Persona persona){
        personaServicio.añadirPersona(persona);
    }
}
