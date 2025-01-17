package org.iesch.ad.CRUD.Mongo.DB.servicio;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.iesch.ad.CRUD.Mongo.DB.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicio {

    @Autowired
    PersonaRepositorio personaRepositorio;

    public List<Persona> getTodos() {
        return personaRepositorio.findAll();
    }

    public void añadirPersona(Persona persona) {
        personaRepositorio.insert(persona);
    }
}
