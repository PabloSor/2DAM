package org.iesch.EjercicioMongo2.objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.ArrayList;

@Document
public class Persona {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private Integer edad;
    private String ciudad;
    private ArrayList<String> intereses;
    private Direccion direccion;

    public Persona(String nombre, String apellido, Integer edad, String ciudad, ArrayList<String> intereses, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.intereses = intereses;
        this.direccion = direccion;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(ArrayList<String> intereses) {
        this.intereses = intereses;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
