package com.emergente.mongo.entidades;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrdenDetalle {

    @Id
    private ObjectId id;
    private Articulo articulo;
    private Suplidor suplidor;
    private int cantidad;

    public OrdenDetalle() {
    }

    public OrdenDetalle(Articulo articulo, Suplidor suplidor, int cantidad) {
        this.articulo = articulo;
        this.suplidor = suplidor;
        this.cantidad = cantidad;
    }

    public OrdenDetalle(ObjectId id, Articulo articulo, Suplidor suplidor, int cantidad) {
        this.id = id;
        this.articulo = articulo;
        this.suplidor = suplidor;
        this.cantidad = cantidad;
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

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
