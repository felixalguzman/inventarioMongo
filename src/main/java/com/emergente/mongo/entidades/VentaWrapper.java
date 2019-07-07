package com.emergente.mongo.entidades;

import java.util.LinkedHashMap;
import java.util.List;

public class VentaWrapper {

    private String articulo;
    private int cantidad;


    public VentaWrapper() {
    }

    public VentaWrapper(String articulo, int cantidad) {
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
