package com.emergente.mongo.entidades;

public class Articulos{

    private String articulo;
    private int cantidad;

    public Articulos() {
    }

    public Articulos(String articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
