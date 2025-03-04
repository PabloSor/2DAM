package org.iesch.EjercicioMongo2.repositorio;

import org.iesch.EjercicioMongo2.objects.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepositorio extends MongoRepository<Persona, String> {
    List<Persona> findByEdadBetween(int minEdad, int maxEdad);
}
