package org.iesch.ad.Ej1.service;

import org.iesch.ad.Ej1.model.Estudiante;
import org.iesch.ad.Ej1.repo.EstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    EstudianteRepo estudianteRepo;


    public Object consulta1(Integer grade){
        return Criteria.where("grade").gt(grade);
    }
}
