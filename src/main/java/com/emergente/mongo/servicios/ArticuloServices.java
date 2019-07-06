package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.repositorios.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticuloServices {

    private final ArticuloRepository articuloRepository;

    public ArticuloServices(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Transactional
    public void crear(Articulo articulo) {
        articuloRepository.save(articulo);
    }

    public List<Articulo> getAll() {
        return articuloRepository.findAll();
    }

    public Articulo buscarPorNombre(String nombre) {
        return articuloRepository.findByNombre(nombre);
    }

    public Articulo buscarPorDescripcion(String descripcion) {
        return articuloRepository.findByDescripcion(descripcion);
    }

    public void actualizarStockCompra(Articulo articulo, int nuevoStock) {

        articulo.setStock(articulo.getStock() + nuevoStock);
        crear(articulo);

    }

    public void actualizarStockVenta(Articulo articulo, int nuevoStock) {

        articulo.setStock(articulo.getStock() - nuevoStock);
        crear(articulo);

    }
}
