package com.iesch.ad.ProductoRest.repository;

import com.iesch.ad.ProductoRest.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
