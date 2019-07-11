package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DetalleMovimiento {

    @Id
    private ObjectId id;

    private Articulo articulo;
    private int cantidadMovimiento;

    public DetalleMovimiento() {
    }

    public DetalleMovimiento(Articulo articulo, int cantidadMovimiento) {
        this.articulo = articulo;
        this.cantidadMovimiento = cantidadMovimiento;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidadMovimiento() {
        return cantidadMovimiento;
    }

    public void setCantidadMovimiento(int cantidadMovimiento) {
        this.cantidadMovimiento = cantidadMovimiento;
    }

}
