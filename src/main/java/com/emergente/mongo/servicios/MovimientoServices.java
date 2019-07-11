package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.*;
import com.emergente.mongo.repositorios.MovimientoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MovimientoServices {

    final
    MongoTemplate mongoTemplate;
    final ArticuloServices articuloServices;
    private final MovimientoRepository movimientoRepository;

    public MovimientoServices(MongoTemplate mongoTemplate, ArticuloServices articuloServices, MovimientoRepository movimientoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.articuloServices = articuloServices;
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

    public List<Promedio> average() {

        Aggregation aggregation = Aggregation.newAggregation(
                project("detalleMovimiento", "tipoMovimiento", "fecha"),
                unwind("detalleMovimiento"),
                match(Criteria.where("tipoMovimiento").is(TipoMovimiento.SALIDA)),
                project("tipoMovimiento").and("fecha").extractDayOfYear().as("dia")
                        .and("detalleMovimiento.articulo._id").as("articuloId")
                        .and("detalleMovimiento.cantidadMovimiento").as("cantidad"),
                group("articuloId", "tipoMovimiento").avg("cantidad").as("promedio")
//                project("promedio").and("articuloId").previousOperation().and("tipoMovimiento").previousOperation().and("dia").previousOperation()
        );

        AggregationResults<Promedio> groupResults
                = mongoTemplate.aggregate(aggregation, Movimiento.class, Promedio.class);
        List<Promedio> result = groupResults.getMappedResults();

        result.forEach(r -> System.out.println("articulo: " + r.getArticuloId() + " tipo: " + r.getTipoMovimiento() + " promedio: " + r.getPromedio()));

        return result;

    }

    public void buscarDiasEntreFechas(SolicitudOrden orden) {

        long dias = DAYS.between(LocalDate.now(), orden.getFechaEsperada());
        List<Promedio> promedios = average();

        System.out.println("fecha hoy: " + LocalDate.now() + " y esperada: " + orden.getFechaEsperada() + " dias: " + dias);

        Map<String, Double> inventarioNecesario = new HashMap<>();
        Map<String, Double> promedioPorArticulo = new HashMap<>();
        for (Promedio promedio : promedios) {
            double diasDisponibles = dias * promedio.getPromedio();
            inventarioNecesario.put(promedio.getArticuloId(), diasDisponibles);
            promedioPorArticulo.put(promedio.getArticuloId(), promedio.getPromedio());
        }
        System.out.println("Inventario necesario");
        inventarioNecesario.forEach((k, v) -> System.out.println("art: " + k + " dias disponibles: " + v));

        Map<String, Double> excedenteArticulos = new HashMap<>();
        for (Articulo articulo : articuloServices.getAll()) {

            double excedente = articulo.getStock() - inventarioNecesario.get(articulo.get_id());
            excedenteArticulos.put(articulo.get_id(), excedente);
        }


        System.out.println("Excedente articulo");
        excedenteArticulos.forEach((k, v) -> System.out.println("art: " + k + " excedente: " + v));


        Map<String, Double> cantidadRecomendada = new HashMap<>();
        for (SolicitudOrdenDetalle detalle : orden.getDetalles()) {
            double recomendada = detalle.getCantidad() - excedenteArticulos.get(detalle.getArticulo().get_id());
            cantidadRecomendada.put(detalle.getArticulo().get_id(), recomendada);
        }


        System.out.println("Cantidad recomendada");
        cantidadRecomendada.forEach((k, v) -> System.out.println("art: " + k + " cantidad: " + v));


//        Map<String, Double> cantidadRecomendada = new HashMap<>();
        System.out.println("Calcular dias faltantes a cada articulo");
        List<Double> diasFaltantes = new ArrayList<>();
        for (Map.Entry<String, Double> entry : excedenteArticulos.entrySet()) {

            System.out.println("art: " + entry.getKey() + " valor: " + entry.getValue());
            if (entry.getValue() < 0) {

                double diaFaltante = entry.getValue() / promedioPorArticulo.get(entry.getKey());
                diasFaltantes.add(diaFaltante);

            }
//            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        diasFaltantes.forEach(d -> System.out.println("dias faltantes: " + d));

        double minimoDia = diasFaltantes.get(diasFaltantes.indexOf(Collections.min(diasFaltantes)));
        orden.setFechaEsperada(orden.getFechaEsperada().plusDays((long) minimoDia));

        System.out.println("fecha esperada nueva: " + orden.getFechaEsperada());
        List<String> ids = new ArrayList<>();

        orden.getDetalles().forEach(d -> ids.add(d.getArticulo().get_id()));


        Aggregation aggregation = Aggregation.newAggregation(
                unwind("suplidorDetalle"),
                match(Criteria.where("suplidorDetalle.articulo._id").in(ids)),
                project("_id")
        );

    }


}
