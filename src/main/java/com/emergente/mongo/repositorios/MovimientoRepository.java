package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Movimiento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovimientoRepository extends MongoRepository<Movimiento, ObjectId> {


}
