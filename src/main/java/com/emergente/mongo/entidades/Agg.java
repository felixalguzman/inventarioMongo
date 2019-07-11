package com.emergente.mongo.entidades;

public class Agg {

    private String tipoMovimiento;
    private int cantidad;

    public Agg(String tipoMovimiento, int cantidad) {
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
    }

    public Agg() {
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
