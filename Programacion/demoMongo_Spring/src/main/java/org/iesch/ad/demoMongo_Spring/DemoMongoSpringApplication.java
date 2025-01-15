package org.iesch.ad.demoMongo_Spring;

import org.iesch.ad.demoMongo_Spring.modelo.Trabajador;
import org.iesch.ad.demoMongo_Spring.repsitorio.TrabajadorRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoMongoSpringApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoMongoSpringApplication.class, args);
		var repo = context.getBean(TrabajadorRepo.class);

		// Ejemplo1:
		List<Trabajador> trabajadores = List.of(
				new Trabajador(null, "Juan", "juan@iesch.org", LocalDate.of(1979, 3, 11)),
				new Trabajador(null, "Antonio", "toni@iesch.org", LocalDate.of(1987, 3, 27)),
				new Trabajador(null, "Pedro", "pedro@iesch.org", LocalDate.of(1990, 8, 21)),
				new Trabajador(null, "Julio", "julio@iesch.org", LocalDate.of(19793, 1, 3)),
				new Trabajador(null, "Diego", "diego@iesch.org", LocalDate.of(2005, 9, 17)),
				new Trabajador(null, "Paco", "paco@iesch.org", LocalDate.of(1972, 11, 8))
		);

		repo.saveAll(trabajadores);
		repo.findAll().forEach(System.out::println);
	}
}
