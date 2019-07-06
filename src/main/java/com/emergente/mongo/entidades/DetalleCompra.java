package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DetalleCompra {

    @Id
    private ObjectId id;

    private Articulo articulo;
    private int cantidadStock;
    private double subTotal;

    public DetalleCompra() {
    }

    public DetalleCompra(Articulo articulo, int cantidadStock, double subTotal) {
        this.articulo = articulo;
        this.cantidadStock = cantidadStock;
        this.subTotal = subTotal;
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

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
