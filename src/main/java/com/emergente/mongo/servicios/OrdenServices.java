package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.*;
import com.emergente.mongo.repositorios.OrdenRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrdenServices {

    final
    MongoTemplate mongoTemplate;

    final SuplidorServices suplidorServices;
    final ArticuloServices articuloServices;
    private final OrdenRepository ordenRepository;

    public OrdenServices(MongoTemplate mongoTemplate, SuplidorServices suplidorServices, ArticuloServices articuloServices, OrdenRepository ordenRepository) {
        this.mongoTemplate = mongoTemplate;
        this.suplidorServices = suplidorServices;
        this.articuloServices = articuloServices;
        this.ordenRepository = ordenRepository;
    }

    @Transactional
    public Orden crear(List<SuplidorTemporal> detalles, LocalDate fecha) {


        Orden orden = new Orden();
        List<OrdenDetalle> ordenDetalles = new ArrayList<>();

        double total = 0.0;

        List<Integer> tiempoEntregaSuplidores = new ArrayList<>();
        for (SuplidorTemporal temporal : detalles) {

            Articulo articulo = articuloServices.buscarPorId(temporal.getArticuloId());
            Suplidor suplidor = suplidorServices.buscarPorId(temporal.getSuplidorId());

            suplidor.getSuplidorDetalles().forEach(d -> tiempoEntregaSuplidores.add(d.getTiempoEntrega()));
//            doubles.add(suplidor.)
            System.out.println("cantidad: " + temporal.getCantidad());
            OrdenDetalle ordenDetalle = new OrdenDetalle(articulo, suplidor, temporal.getCantidad());
            ordenDetalles.add(ordenDetalle);

            total += articulo.getPrecio() * temporal.getCantidad();
        }

        orden.setDetalles(ordenDetalles);
        orden.setFechaEntrega(fecha.minusDays(tiempoEntregaSuplidores.get(tiempoEntregaSuplidores.indexOf(Collections.max(tiempoEntregaSuplidores)))));
        orden.setTotal(total);

        return ordenRepository.save(orden);


    }

    public List<Orden> getAll() {
        return ordenRepository.findAll();
    }
}
