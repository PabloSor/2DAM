package org.iesch.ad.demoMongo_Spring;

import org.iesch.ad.demoMongo_Spring.modelo.Trabajador;
import org.iesch.ad.demoMongo_Spring.repsitorio.TrabajadorRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoMongoSpringApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoMongoSpringApplication.class, args);
		var repo = context.getBean(TrabajadorRepo.class);

		// Ejemplo1:
		List<Trabajador> trabajadores = List.of(
				new Trabajador(null, "Juan", "juan@iesch.org", LocalDate.of(1979, 3, 11), 180),
				new Trabajador(null, "Antonio", "toni@iesch.org", LocalDate.of(1987, 3, 27), 175),
				new Trabajador(null, "Pedro", "pedro@iesch.org", LocalDate.of(1990, 8, 21), 184),
				new Trabajador(null, "Julio", "julio@iesch.org", LocalDate.of(19793, 1, 3), 179),
				new Trabajador(null, "Diego", "diego@iesch.org", LocalDate.of(2005, 9, 17), 193),
				new Trabajador(null, "Paco", "paco@iesch.org", LocalDate.of(1972, 11, 8), 172)
		);

		// Para guardar y ver
		//repo.saveAll(trabajadores);
		//repo.findAll().forEach(System.out::println);

		// Para borrar todos
		//repo.deleteAll();

		// Numero de trabajadores que se llaman x
		System.out.println("Numero de trabajadores con nombre <Julio>  -->  "+repo.countAllByNombre("Julio"));
		System.out.println("Numero de trabajadores con nombre <Pedro>  -->  "+repo.contarPorNombre("Pedro"));

		// Mongo template
		Query query = new Query();
		query.addCriteria(Criteria.where("nombre").is("Diego"));

		var mongoTemplate = context.getBean(MongoTemplate.class);
		System.out.println("Trabajadores con nombre <Diego>  -->  "+mongoTemplate.count(query, Trabajador.class));

		// Crear y guardar uno
		//Trabajador trabajador = new Trabajador(null, "Alberto", "alberto@iesch.org", LocalDate.of(2005, 8, 14), 187);
		//mongoTemplate.save(trabajador);

		// Mostrar todos
		System.out.println("Todos los trabajadores");
		List<Trabajador> listaTodos = mongoTemplate.findAll(Trabajador.class);
		listaTodos.forEach(System.out::println);

		// Buscar por id
		String id = "678a91526c9ba5370c86b65d";
		System.out.println("Buscando por id: "+mongoTemplate.findById(id, Trabajador.class));

		// Buscar por nombre:
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("nombre").is("Paco"));
		System.out.println("Trabajadores con nombre <Paco>  -->  "+mongoTemplate.count(query1, Trabajador.class));

		// Buscar con and:
		Query queryAnd = new Query();
		queryAnd.addCriteria(Criteria.where("nombre").is("Juan").and("email").is("juan@iesch.org"));
		System.out.println("Trabajadores con nombre <Juan> y email <juan@iesch.org>  -->  "+mongoTemplate.count(query1, Trabajador.class));

		// Buscar con or:
		Query queryOr = new Query();
		queryOr.addCriteria(new Criteria().orOperator(Criteria.where("altura").is("175").and("nombre").is("Juan")));
		List<Trabajador> listaOr = mongoTemplate.find(queryOr, Trabajador.class);
		System.out.println("Trabajadores con nombre <Juan> o altura <175>");
		listaOr.forEach(System.out::println);

	}
}
