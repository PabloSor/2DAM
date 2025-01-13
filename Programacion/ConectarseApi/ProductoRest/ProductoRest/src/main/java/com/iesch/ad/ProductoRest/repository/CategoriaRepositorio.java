package com.iesch.ad.ProductoRest.repository;

import com.iesch.ad.ProductoRest.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
}
