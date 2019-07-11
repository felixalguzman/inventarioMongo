package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.DetalleMovimiento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetalleCompraRepository extends MongoRepository<DetalleMovimiento, ObjectId> {
}
