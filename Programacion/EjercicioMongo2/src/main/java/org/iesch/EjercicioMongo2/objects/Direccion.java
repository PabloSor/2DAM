package org.iesch.EjercicioMongo2.objects;

public class Direccion {
    private String calle;
    private Integer numero;
    private String ciudad;

    public Direccion(String calle, Integer numero, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public Direccion() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
