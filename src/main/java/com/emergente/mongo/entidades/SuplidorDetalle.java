package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SuplidorDetalle {

    @Id
    private ObjectId id;

    private int tiempoEntrega;
    private double precio;
    private Articulo articulo;


    public SuplidorDetalle() {
    }

    public SuplidorDetalle(int tiempoEntrega, double precio, Articulo articulo) {
        this.tiempoEntrega = tiempoEntrega;
        this.precio = precio;
        this.articulo = articulo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
