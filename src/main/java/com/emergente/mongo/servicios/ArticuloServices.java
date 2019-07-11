package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.entidades.DetalleMovimiento;
import com.emergente.mongo.entidades.Movimiento;
import com.emergente.mongo.entidades.TipoMovimiento;
import com.emergente.mongo.repositorios.ArticuloRepository;
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

    public Articulo buscarPorId(String id) {
        return articuloRepository.findById(id).orElse(null);
    }

    public void actualizarStock(Movimiento movimiento) {

        if (movimiento.getTipoMovimiento() == TipoMovimiento.ENTRADA) {
            System.out.println("Sumar");
            for (DetalleMovimiento detalleMovimiento : movimiento.getDetalleMovimiento()) {

                actualizarStockCompra(detalleMovimiento.getArticulo(), detalleMovimiento.getCantidadMovimiento());
            }

        } else if (movimiento.getTipoMovimiento() == TipoMovimiento.SALIDA){
            System.out.println("vender");
            for (DetalleMovimiento detalleMovimiento : movimiento.getDetalleMovimiento()) {

                actualizarStockVenta(detalleMovimiento.getArticulo(), detalleMovimiento.getCantidadMovimiento());
            }
        }
    }
}
