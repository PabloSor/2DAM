package org.iesch.ad.CRUD.Mongo.DB.controlador;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.iesch.ad.CRUD.Mongo.DB.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(personaServicio.añadirPersona(persona));
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<?> buscarPersona(@PathVariable String id){
        return ResponseEntity.ok(personaServicio.buscarPorId(id));
    }

    @PutMapping("/persona/{id}")
    public ResponseEntity<?> actualizarPersona(@RequestBody Persona person, @PathVariable String id){
        Persona persona = personaServicio.buscarPorId(id);

        if (persona == null){
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        personaServicio.updatePersona(person);
        return  ResponseEntity.ok(person);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable String id){
        if (personaServicio.buscarPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        personaServicio.borrarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
