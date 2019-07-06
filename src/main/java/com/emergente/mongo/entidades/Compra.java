package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document
public class Compra {

    @Id
    private ObjectId id;
    private Set<DetalleCompra> detalleCompra;
    private LocalDate fecha;
    private Suplidor suplidor;


    public Compra() {
    }

    public Compra(Set<DetalleCompra> detalleCompra, LocalDate fecha, Suplidor suplidor) {
        this.detalleCompra = detalleCompra;
        this.fecha = fecha;
        this.suplidor = suplidor;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Set<DetalleCompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(Set<DetalleCompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(Suplidor suplidor) {
        this.suplidor = suplidor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
