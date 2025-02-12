package org.iesch.ad.Ejercicios_Thymeleaf_Spring.Repository;

import org.iesch.ad.Ejercicios_Thymeleaf_Spring.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepo extends MongoRepository<Vehiculo, String> {
}
