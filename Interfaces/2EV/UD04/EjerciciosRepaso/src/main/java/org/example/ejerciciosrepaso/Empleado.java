package org.example.ejerciciosrepaso;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String localidad;

    public Empleado(String nombre, String apellidos, String localidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
