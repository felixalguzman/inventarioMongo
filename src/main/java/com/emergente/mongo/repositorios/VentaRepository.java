package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Venta;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VentaRepository extends MongoRepository<Venta, ObjectId> {
}
