package com.emergente.mongo.repositorios;

import com.emergente.mongo.entidades.Articulo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticuloRepository extends MongoRepository<Articulo, String> {

    Articulo findByNombre(String nombre);

    Articulo findByDescripcion(String descripcion);

    List<Articulo> findAllBy_idIn(List<String> ids);
}
