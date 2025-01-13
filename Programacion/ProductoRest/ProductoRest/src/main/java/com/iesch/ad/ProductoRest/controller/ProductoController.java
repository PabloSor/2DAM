package com.iesch.ad.ProductoRest.controller;

import com.iesch.ad.ProductoRest.dto.ProductoDTO;
import com.iesch.ad.ProductoRest.dto.ProductoDTOconverter;
import com.iesch.ad.ProductoRest.modelo.Producto;
import com.iesch.ad.ProductoRest.repository.ProductoRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Tag(name = "Productos", description = "Api para la gestion de productos")
public class ProductoController {

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    ProductoDTOconverter productoDTOconverter;

    //404 si hay productos | 200 si no hay productos

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("api/productos")
    public ResponseEntity<?> obtenerTodos(){
        List<Producto> resultado = productoRepositorio.findAll();
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(resultado);
    }

    //Para arreglar lo de los header de manera chapucera

    //@CrossOrigin(origins = "http://localhost:9999")
    @Operation(summary = "Obtener todos los productos a traves de un DTO")
    @GetMapping("api/productoDTO")
    public  ResponseEntity<?> obtenerTodosAtravesDTO(){

        List<Producto> resultado = productoRepositorio.findAll();
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            List<ProductoDTO> resultadoDTO  = resultado.stream().map(productoDTOconverter::convertToDto).toList();
            return ResponseEntity.ok(resultadoDTO);
        }
    }

    @GetMapping("saluda")
    public ResponseEntity<?> saluda(){
        return ResponseEntity.ok("El saludo es hola!!");

    }
}
