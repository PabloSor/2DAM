package org.iesch.ad.Ej2.repo;

import org.iesch.ad.Ej2.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends MongoRepository<Persona, String> {

}
