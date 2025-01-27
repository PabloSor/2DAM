package org.iesch.EjercicioMongo2.service;

import org.iesch.EjercicioMongo2.objects.Persona;
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

    public List<Persona> buscarCiudad(String ciudad) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ciudad").is(ciudad));

        return mongoTemplate.find(query, Persona.class);
    }
}
