package org.iesch.ad.demoMongo_Spring.repsitorio;

import org.iesch.ad.demoMongo_Spring.modelo.Trabajador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TrabajadorRepo extends MongoRepository<Trabajador, String> {

    public Long countAllByNombre(String nombre);

    @Query(value = "{nombre: ?0}", count = true)
    public Long contarPorNombre(String nombre);
}
