package com.emergente.mongo.entidades;

import java.time.LocalDate;
import java.util.List;

public class SolicitudOrdenDetalleWrapper {

  private List<Articulos> articulos;
    private LocalDate fechaEsperada;


    public SolicitudOrdenDetalleWrapper() {
    }

    public List<Articulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulos> articulos) {
        this.articulos = articulos;
    }

    public LocalDate getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(LocalDate fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public SolicitudOrdenDetalleWrapper(List<Articulos> articulos, LocalDate fechaEsperada) {
        this.articulos = articulos;
        this.fechaEsperada = fechaEsperada;
    }
}
