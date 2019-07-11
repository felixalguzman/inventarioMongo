package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Orden {

    @Id
    private ObjectId id;
   private List<OrdenDetalle> detalles;

    private LocalDate fechaEntrega;
    private double total;

    public Orden() {
    }

    public Orden(ObjectId id, List<OrdenDetalle> detalles, LocalDate fechaEntrega, double total) {
        this.id = id;
        this.detalles = detalles;
        this.fechaEntrega = fechaEntrega;
        this.total = total;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<OrdenDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdenDetalle> detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
