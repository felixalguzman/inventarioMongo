package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document
public class Movimiento {

    @Id
    private ObjectId id;
    private Set<DetalleMovimiento> detalleMovimiento;
    private LocalDate fecha;
    private TipoMovimiento tipoMovimiento;


    public Movimiento() {
    }


    public Movimiento(Set<DetalleMovimiento> detalleMovimiento, LocalDate fecha, TipoMovimiento tipoMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Set<DetalleMovimiento> getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(Set<DetalleMovimiento> detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
