package org.iesch.empleados;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private int annosEnEmpresa;
    private float salario;
    private int telefono;

    private Empleado supervisor;

    public Empleado(String nombre, String apellidos, String dni, String direccion, int telefono, float salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.salario = salario;
        this.telefono = telefono;
    }

    public Empleado(String nombre, String apellidos, String dni, String direccion, int annosEnEmpresa, float salario, int telefono, Empleado supervisor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.annosEnEmpresa = annosEnEmpresa;
        this.salario = salario;
        this.telefono = telefono;
        this.supervisor = supervisor;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAnnosEnEmpresa() {
        return annosEnEmpresa;
    }

    public void setAnnosEnEmpresa(int annosEnEmpresa) {
        this.annosEnEmpresa = annosEnEmpresa;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Empleado getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    public void cambiarSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    public void incrementarSalario(float cantidad){
        this.salario+=cantidad;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", annosEnEmpresa=" + annosEnEmpresa +
                ", salario=" + salario +
                ", telefono=" + telefono +
                ", supervisor=" + supervisor +
                '}';
    }
}
