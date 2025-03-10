package org.iesch.ad.Ej1.repo;

import org.iesch.ad.Ej1.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepo extends MongoRepository<Estudiante, String> {
}
