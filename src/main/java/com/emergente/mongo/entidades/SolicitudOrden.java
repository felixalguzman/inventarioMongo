package com.emergente.mongo.entidades;

import java.time.LocalDate;
import java.util.List;

public class SolicitudOrden {

    private List<SolicitudOrdenDetalle> detalles;
    private LocalDate fechaEsperada;
    private LocalDate fechaActual;


    public SolicitudOrden() {
    }

    public SolicitudOrden(List<SolicitudOrdenDetalle> detalles, LocalDate fechaEsperada, LocalDate fechaActual) {
        this.detalles = detalles;
        this.fechaEsperada = fechaEsperada;
        this.fechaActual = fechaActual;
    }

    public List<SolicitudOrdenDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<SolicitudOrdenDetalle> detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(LocalDate fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }
}
