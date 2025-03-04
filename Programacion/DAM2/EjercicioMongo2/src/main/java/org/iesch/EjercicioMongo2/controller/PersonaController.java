package org.iesch.EjercicioMongo2.controller;

import jakarta.websocket.server.PathParam;
import org.iesch.EjercicioMongo2.objects.Persona;
import org.iesch.EjercicioMongo2.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/personaciudad/{ciudad}")
    public ResponseEntity<?> personaPorCiudad(@PathVariable String ciudad){
        return ResponseEntity.ok(personaService.buscarCiudad(ciudad));
    }

    @GetMapping("/personaletra/{letra}")
    public ResponseEntity<?> personaLetra(@PathVariable String letra){
        if (letra.length() > 1){
            return ResponseEntity.badRequest().body("Máximo una letra");
        }

        return ResponseEntity.ok(personaService.letraNombre(letra));
    }

    @PostMapping("/personanueva")
    public ResponseEntity<?> personaNuea(@RequestBody Persona persona){
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }

    @GetMapping("/personaedad")
    public ResponseEntity<?> personaEdad(@RequestParam Integer edadMin, @RequestParam Integer edadMax){
        return ResponseEntity.ok(personaService.buscarEdad(edadMin, edadMax));
    }

    @GetMapping("/personainteres")
    public ResponseEntity<?> personaInteres(@RequestParam String interes){
        return ResponseEntity.ok(personaService.buscarInteres(interes));
    }

    @GetMapping("/personaletraciudad/{letra}")
    public ResponseEntity<?> personaLetraciudad(@PathVariable String letra){
        if (letra.length() > 1){
            return ResponseEntity.badRequest().body("Máximo una letra");
        }

        return ResponseEntity.ok(personaService.letraCiudad(letra));
    }

    @GetMapping("/personaletraapellido/{letra}")
    public ResponseEntity<?> personaLetraapellido(@PathVariable String letra){
        if (letra.length() > 1){
            return ResponseEntity.badRequest().body("Máximo una letra");
        }

        return ResponseEntity.ok(personaService.letraApellido(letra));
    }

    @GetMapping("/personaedadciudad")
    public ResponseEntity<?> personaCiudadApellido(@RequestParam Integer edad, @RequestParam Integer letrasCiudad){
        return ResponseEntity.ok(personaService.personaEdadCiudad(edad, letrasCiudad));
    }
}
