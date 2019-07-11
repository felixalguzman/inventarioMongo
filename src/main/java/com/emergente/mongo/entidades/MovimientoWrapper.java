package com.emergente.mongo.entidades;

import java.util.List;

public class MovimientoWrapper {


    private List<Articulos> articulos;
    private int tipo;

    public MovimientoWrapper() {
    }

    public MovimientoWrapper(List<Articulos> articulos, int tipo) {
        this.articulos = articulos;
        this.tipo = tipo;
    }

    public List<Articulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulos> articulos) {
        this.articulos = articulos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

