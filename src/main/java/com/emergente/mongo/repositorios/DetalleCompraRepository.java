package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.DetalleCompra;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetalleCompraRepository extends MongoRepository<DetalleCompra, ObjectId> {
}
