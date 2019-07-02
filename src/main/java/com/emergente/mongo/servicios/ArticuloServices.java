package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.repositorios.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServices {

    private final ArticuloRepository articuloRepository;

    public ArticuloServices(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Transactional
    public void crear(Articulo articulo){
        articuloRepository.save(articulo);
    }
}
