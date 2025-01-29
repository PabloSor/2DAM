package org.iesch.ad.demosPendientesUD04.controlador;

import jakarta.validation.Valid;
import org.iesch.ad.demosPendientesUD04.modelo.Usuario;
import org.iesch.ad.demosPendientesUD04.repsitorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Usuariocontroller {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/usuario")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody Usuario usuario){
        try {
            if (usuario.getPassword().equals(usuario.getPasswordConfirm())){
                return ResponseEntity.ok(usuarioRepositorio.save(usuario));
            }else {
                System.out.println("Las contraseñas no coinciden");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }catch (ResponseStatusException e){
            System.out.println(e.getReason());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
        }

    }
}
