package org.iesch.ad.DemoFiltros_ModelosUsuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Para que funcione la auditoria de JPA
public class DemoFiltrosModelosUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFiltrosModelosUsuarioApplication.class, args);
	}

}
