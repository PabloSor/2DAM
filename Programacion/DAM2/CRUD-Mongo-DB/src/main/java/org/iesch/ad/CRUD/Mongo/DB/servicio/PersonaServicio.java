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

    public Object añadirPersona(Persona persona) {
        return personaRepositorio.save(persona);
    }


    public Persona buscarPorId(String id) {
        return personaRepositorio.findById(id).orElse(null);
    }

    public Persona updatePersona(Persona person) {
        return personaRepositorio.save(person);
    }

    public void borrarPersona(String id) {
        personaRepositorio.deleteById(id);
    }
}
