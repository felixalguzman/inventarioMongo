package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;

public class SuplidorTemporal {

    private ObjectId suplidorId;
    private String articuloId;
    private int cantidad;

    public SuplidorTemporal() {
    }

    public SuplidorTemporal(ObjectId suplidorId, String articuloId, int cantidad) {
        this.suplidorId = suplidorId;
        this.articuloId = articuloId;
        this.cantidad = cantidad;
    }

    public ObjectId getSuplidorId() {
        return suplidorId;
    }

    public void setSuplidorId(ObjectId suplidorId) {
        this.suplidorId = suplidorId;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
