package org.iesch.ad.Ejercicios_Thymeleaf_Spring.model;



import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//su matrícula, su marca, su modelo, su cilindrada, su potencia, el color,
//añade un campo para la fecha de matriculación y asegúrate de que la fecha introducida sea
//válida y anterior a la fecha actual

@Document
public class Vehiculo {

    @Id
    private String matricula;

    private String marca;
    private String modelo;
    private Integer cc_cilindrada;
    private Integer cv_potencia;
    private String color;

    @CreatedDate
    private LocalDateTime fechaMatriculacion;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String marca, String modelo, Integer cc_cilindrada, Integer cv_potencia, String color, LocalDateTime fechaMatriculacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cc_cilindrada = cc_cilindrada;
        this.cv_potencia = cv_potencia;
        this.color = color;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCc_cilindrada() {
        return cc_cilindrada;
    }

    public void setCc_cilindrada(Integer cc_cilindrada) {
        this.cc_cilindrada = cc_cilindrada;
    }

    public Integer getCv_potencia() {
        return cv_potencia;
    }

    public void setCv_potencia(Integer cv_potencia) {
        this.cv_potencia = cv_potencia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDateTime fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }
}
