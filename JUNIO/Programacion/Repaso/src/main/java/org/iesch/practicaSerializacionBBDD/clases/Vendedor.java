package org.iesch.practicaSerializacionBBDD.clases;

public class Vendedor {
    private int id;
    private String nombre;
    private String empresa;
    private String contacto;

    public Vendedor(int id, String nombre, String empresa, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.contacto = contacto;
    }

    public Vendedor() {
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empresa='" + empresa + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
