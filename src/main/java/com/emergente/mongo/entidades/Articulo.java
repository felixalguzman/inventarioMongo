package com.emergente.mongo.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Articulo {

    @Id
    private String id;

    private String descripcion;

    private int cantidadActual;

    public Articulo() {
    }

    public Articulo(String descripcion, int cantidadActual) {
        this.descripcion = descripcion;
        this.cantidadActual = cantidadActual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}
