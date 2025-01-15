package org.iesch.ad.demoMongo_Spring.repsitorio;

import org.iesch.ad.demoMongo_Spring.modelo.Trabajador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepo extends MongoRepository<Trabajador, String> {

}
