package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Agg;
import com.emergente.mongo.entidades.Movimiento;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraServices {

    final
    MongoTemplate mongoTemplate;

    public OrdenCompraServices(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


}
