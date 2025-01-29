package org.iesch.EjercicioMongo2.repositorio;

import org.iesch.EjercicioMongo2.objects.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepositorio extends MongoRepository<Persona, String> {
}
