package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.entidades.Suplidor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplidorRepository extends MongoRepository<Suplidor, ObjectId> {

    Suplidor findByNombre(String nombre);

}
