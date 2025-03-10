package org.iesch.ad.Ej1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Estudiante {

    @Id
    private String id;
    private String name;
    private Integer age;

    private Address address;
    private ArrayList<Course> courses;
    private ArrayList<Extracurricular> extracurriculars;

    public Estudiante() {
    }

    public Estudiante(String id, String name, Integer age, Address address, ArrayList<Course> courses, ArrayList<Extracurricular> extracurriculars) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.courses = courses;
        this.extracurriculars = extracurriculars;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Extracurricular> getExtracurriculars() {
        return extracurriculars;
    }

    public void setExtracurriculars(ArrayList<Extracurricular> extracurriculars) {
        this.extracurriculars = extracurriculars;
    }
}
