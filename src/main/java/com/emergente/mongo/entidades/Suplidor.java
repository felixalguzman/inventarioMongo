package com.emergente.mongo.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Suplidor {

    @Id
    private String id;

    private String nombre;


    private Set<Articulo> articulos;

    public Suplidor() {
    }

    public Suplidor(String nombre, Set<Articulo> articulos) {
        this.nombre = nombre;
        this.articulos = articulos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }
}
