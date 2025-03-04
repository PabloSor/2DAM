package org.iesch.EjercicioMongo2.service;

import org.iesch.EjercicioMongo2.objects.Persona;
import org.iesch.EjercicioMongo2.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PersonaRepositorio personaRepositorio;

    public List<Persona> buscarCiudad(String ciudad) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ciudad").is(ciudad));

        return mongoTemplate.find(query, Persona.class);
    }

    public List<Persona> letraNombre(String letra){
        Query query = new Query();
        query.addCriteria(Criteria.where("nombre").regex("^"+letra,"i"));

        return mongoTemplate.find(query, Persona.class);
    }

    public Object crearPersona(Persona persona) {
        return mongoTemplate.save(persona);
    }

    public Object buscarEdad(Integer edadMin, Integer edadMax) {
        return personaRepositorio.findByEdadBetween(edadMin, edadMax);
    }

    public Object buscarInteres(String interes) {
        Query query = new Query();
        query.addCriteria(Criteria.where("intereses").is(interes));

        return mongoTemplate.find(query, Persona.class);
    }

    public Object letraCiudad(String letra) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ciudad").regex("^"+letra,"i"));

        return mongoTemplate.find(query, Persona.class);
    }

    public Object letraApellido(String letra) {
        Query query = new Query();
        query.addCriteria(Criteria.where("apellido").regex(letra+"$","i"));

        return mongoTemplate.find(query, Persona.class);
    }

    public Object personaEdadCiudad(Integer edad, Integer letrasCiudad) {
        Query query = new Query();
        query.addCriteria(Criteria.where("edad").gte(edad).and("ciudad").regex("^.{"+letrasCiudad+"}$"));

        return mongoTemplate.find(query, Persona.class);
    }
}
