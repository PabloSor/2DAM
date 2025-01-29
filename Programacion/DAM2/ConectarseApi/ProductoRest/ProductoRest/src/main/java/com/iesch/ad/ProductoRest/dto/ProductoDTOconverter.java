package com.iesch.ad.ProductoRest.dto;



import com.iesch.ad.ProductoRest.modelo.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoDTOconverter {

    @Autowired
    ModelMapper modelMapper;

    //Desde un producto a un ProductoDTO
    public ProductoDTO convertToDto (Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
    }

    //Desde un productoDTO a un producto
    public Producto convertDesdeDTO(ProductoDTOcreado productoDTOcreado){
        return modelMapper.map(productoDTOcreado, Producto.class);
    }
}
