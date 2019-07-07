package com.emergente.mongo.entidades;

public class CompraWrapper {

    private String suplidor;
    private String articulo;
    private int cantidad;

    public CompraWrapper() {
    }

    public CompraWrapper(String suplidor, String articulo, int cantidad) {
        this.suplidor = suplidor;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public String getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(String suplidor) {
        this.suplidor = suplidor;
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
