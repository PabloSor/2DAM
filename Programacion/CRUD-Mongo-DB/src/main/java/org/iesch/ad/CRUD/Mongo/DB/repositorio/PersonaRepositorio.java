package org.iesch.ad.CRUD.Mongo.DB.repositorio;

import org.iesch.ad.CRUD.Mongo.DB.modelo.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepositorio extends MongoRepository<Persona, String> {
}
