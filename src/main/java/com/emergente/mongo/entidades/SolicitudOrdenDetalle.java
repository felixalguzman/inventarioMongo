package com.emergente.mongo.entidades;

public class SolicitudOrdenDetalle {

    private Articulo articulo;
    private int cantidad;

    public SolicitudOrdenDetalle() {
    }

    public SolicitudOrdenDetalle(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
