package org.iesch.ad.CRUD.Mongo.DB.servicio;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.iesch.ad.CRUD.Mongo.DB.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaUsingTemplateServicio {

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Persona> getTodos() {
        return mongoTemplate.findAll(Persona.class);
    }

    public Object añadirPersona(Persona persona) {
        return mongoTemplate.save(persona);
    }


    public Persona buscarPorId(String id) {
        return mongoTemplate.findById(id, Persona.class);
    }

    public Persona updatePersona(Persona person) {
        return mongoTemplate.save(person);
    }

    public void borrarPersona(String id) {
        personaRepositorio.deleteById(id);
    }
}
