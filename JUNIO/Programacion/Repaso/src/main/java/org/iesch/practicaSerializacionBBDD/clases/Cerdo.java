package org.iesch.practicaSerializacionBBDD.clases;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Cerdo {
    private int id;
    private String nombre;
    private String raza;
    private String fecha_nacimiento;
    private double peso;

    public Cerdo(int id, String nombre, String raza, String fecha_nacimiento, double peso) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.fecha_nacimiento = fecha_nacimiento;
        this.peso = peso;
    }

    public Cerdo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Cerdo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", peso=" + peso +
                '}';
    }
}
