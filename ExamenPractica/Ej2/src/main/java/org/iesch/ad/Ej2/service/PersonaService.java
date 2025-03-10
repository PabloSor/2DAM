package org.iesch.ad.Ej2.service;

import org.iesch.ad.Ej2.dto.PotenciaDTO;
import org.iesch.ad.Ej2.model.Persona;
import org.iesch.ad.Ej2.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    public Object encontrarTodos() {
        return mongoTemplate.findAll(Persona.class);
    }


    public Object buscarId(String id) {
        return mongoTemplate.findById(id, Persona.class);
    }

    public Object crearPersona(Persona persona) {
        return mongoTemplate.insert(persona);
    }

    public Object actualizarPersona(String id, Persona persona) {
        return null;
    }

    public Object borrarPersona(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("id").regex(id));

        return mongoTemplate.findAndRemove(query, Persona.class);
    }

    public Object potenciaVehiculo(String matricula) {
        PotenciaDTO potenciaDTO = new PotenciaDTO();
        Persona persona;

        Boolean coche = true;

        Integer cilindrada = 0;
        Integer cilindros = 0;

        Double potenciaFiscal = 0.0;

        Query query = new Query();
        query.addCriteria(new Criteria("matricula").regex(matricula));

        persona = mongoTemplate.findAndRemove(query, Persona.class);

        List<Vehiculo> vehiculos = persona.getVehiculos().stream().toList();

        for (Vehiculo i:vehiculos){
            if (i.getMatricula().equals(matricula)){
                cilindrada = i.getCilindrada();
                cilindros = i.getCilindros();
                potenciaDTO.setMatricula(i.getMatricula());

                if (i.getTipo().equalsIgnoreCase("moto")){coche=false;}

                break;
            }
        }

        if (coche){
            potenciaFiscal = Double.valueOf(cilindrada/cilindros);
            potenciaFiscal = Math.pow(potenciaFiscal, 0.6);
            potenciaFiscal = potenciaFiscal*0.8*cilindros;
        }else {
            potenciaFiscal = Double.valueOf(cilindrada/cilindros);
            potenciaFiscal = Math.pow(potenciaFiscal, 0.6);
            potenciaFiscal = potenciaFiscal*0.8;
        }

        potenciaDTO.setPotenciaFiscal(potenciaFiscal);

        return potenciaDTO;

    }
}
