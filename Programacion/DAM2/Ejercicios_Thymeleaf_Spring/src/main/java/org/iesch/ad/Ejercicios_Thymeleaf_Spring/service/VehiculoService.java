package org.iesch.ad.Ejercicios_Thymeleaf_Spring.service;

import org.iesch.ad.Ejercicios_Thymeleaf_Spring.Repository.VehiculoRepo;
import org.iesch.ad.Ejercicios_Thymeleaf_Spring.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    VehiculoRepo vehiculoRepo;

    public List<Vehiculo> getAll(){
        return vehiculoRepo.findAll();
    }
}
