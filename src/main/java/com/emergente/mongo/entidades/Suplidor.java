package com.emergente.mongo.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Suplidor {

    @Id
    private ObjectId id;

    private String nombre;

    private Set<SuplidorDetalle> suplidorDetalles;

    public Suplidor() {
    }

    public Suplidor(String nombre, Set<SuplidorDetalle> suplidorDetalles) {
        this.nombre = nombre;
        this.suplidorDetalles = suplidorDetalles;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<SuplidorDetalle> getSuplidorDetalles() {
        return suplidorDetalles;
    }

    public void setSuplidorDetalles(Set<SuplidorDetalle> suplidorDetalles) {
        this.suplidorDetalles = suplidorDetalles;
    }
}
