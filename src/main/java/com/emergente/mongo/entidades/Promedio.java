package com.emergente.mongo.entidades;

public class Promedio {

    private String articuloId;
    private String tipoMovimiento;
    private double promedio;

    public Promedio() {
    }

    public Promedio(String articuloId, String tipoMovimiento) {
        this.articuloId = articuloId;
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
