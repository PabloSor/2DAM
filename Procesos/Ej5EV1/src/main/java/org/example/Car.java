package org.example;

import java.io.Serializable;

public class Car implements Serializable {
    private String matricula;
    private int caballos;
    private int pistones;
    private int km;

    public Car(String matricula, int caballos, int pistones, int km) {
        this.matricula = matricula;
        this.caballos = caballos;
        this.pistones = pistones;
        this.km = km;
    }

    public Car() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public int getPistones() {
        return pistones;
    }

    public void setPistones(int pistones) {
        this.pistones = pistones;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    @Override
    public String toString() {
        return "Car{" +
                "matricula='" + matricula + '\'' +
                ", caballos=" + caballos +
                ", pistones=" + pistones +
                ", km=" + km +
                '}';
    }
}
