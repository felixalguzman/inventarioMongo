package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Articulo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticuloRepository extends MongoRepository<Articulo, String> {
}
