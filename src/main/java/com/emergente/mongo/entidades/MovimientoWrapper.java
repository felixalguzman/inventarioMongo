package com.emergente.mongo.entidades;

import java.util.List;

public class MovimientoWrapper {


    private List<Articulos> articulos;
    private int tipoMovimiento;

    public MovimientoWrapper() {
    }

    public MovimientoWrapper(List<Articulos> articulos, int tipoMovimiento) {
        this.articulos = articulos;
        this.tipoMovimiento = tipoMovimiento;
    }

    public List<Articulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulos> articulos) {
        this.articulos = articulos;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}

