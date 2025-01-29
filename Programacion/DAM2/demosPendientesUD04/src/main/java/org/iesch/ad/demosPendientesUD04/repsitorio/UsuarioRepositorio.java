package org.iesch.ad.demosPendientesUD04.repsitorio;

import org.iesch.ad.demosPendientesUD04.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
