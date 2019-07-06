package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Compra;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompraRepository extends MongoRepository<Compra, ObjectId> {
}
