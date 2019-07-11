package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Agg;
import com.emergente.mongo.entidades.Movimiento;
import com.emergente.mongo.repositorios.MovimientoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MovimientoServices {

    final
    MongoTemplate mongoTemplate;

    private final MovimientoRepository movimientoRepository;

    public MovimientoServices(MongoTemplate mongoTemplate, MovimientoRepository movimientoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public void crear(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }

    public void buscarAverage() {

        Aggregation aggregation = Aggregation.newAggregation(
                project("tipoMovimiento"),
                group("tipoMovimiento").count().as("cantidad"),
                project("cantidad").and("tipoMovimiento").previousOperation()
        );

        AggregationResults<Agg> groupResults
                = mongoTemplate.aggregate(aggregation, Movimiento.class, Agg.class);
        List<Agg> result = groupResults.getMappedResults();

        result.forEach(r -> System.out.println("total: " + r.getCantidad() + " tipo " + r.getTipoMovimiento()));
    }

    public void average() {

        Aggregation aggregation = Aggregation.newAggregation(
                project("detalleMovimiento"),
                unwind("detalleMovimiento"),
                project("tipoMovimiento").and("fecha").extractDayOfYear().as("dia").and("detalleMovimiento.articulo._id").as("articuloId").and("detalleMovimiento.cantidadMovimiento").as("cantidad"),
                group("articuloId", "tipoMovimiento", "dia")

        );
    }
}
