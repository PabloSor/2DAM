package org.iesch.ad.CRUD.Mongo.DB.controlador;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.iesch.ad.CRUD.Mongo.DB.servicio.PersonaUsingTemplateServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaUsingTemplateControl {

    @Autowired
    PersonaUsingTemplateServicio personaTemplate;

    @GetMapping("/personaT")
    public ResponseEntity<?> getTodos() {
        List<Persona> listaPersonas = personaTemplate.getTodos();
        if (listaPersonas.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaPersonas);
    }

    @PostMapping("/personaT")
    public ResponseEntity<?> añadirPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaTemplate.añadirPersona(persona));
    }

    @GetMapping("/personaT/{id}")
    public ResponseEntity<?> buscarPersona(@PathVariable String id) {
        return ResponseEntity.ok(personaTemplate.buscarPorId(id));
    }

    @PutMapping("/personaT/{id}")
    public ResponseEntity<?> actualizarPersona(@RequestBody Persona person, @PathVariable String id) {
        Persona persona = personaTemplate.buscarPorId(id);

        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        personaTemplate.updatePersona(person);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/personaT/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable String id) {
        if (personaTemplate.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        personaTemplate.borrarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
