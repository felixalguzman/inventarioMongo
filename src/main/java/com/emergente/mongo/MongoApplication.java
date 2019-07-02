package com.emergente.mongo;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.entidades.Suplidor;
import com.emergente.mongo.repositorios.ArticuloRepository;
import com.emergente.mongo.servicios.ArticuloServices;
import com.emergente.mongo.servicios.SuplidorServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
//		SpringApplication.run(MongoApplication.class, args);

        ApplicationContext context = SpringApplication.run(MongoApplication.class, args);

        ArticuloServices articuloServices = (ArticuloServices) context.getBean("articuloServices");


        Articulo articulo = new Articulo("una cosa", 5);
        Articulo articulo2 = new Articulo("dos cosa", 6);
        articuloServices.crear(articulo);
        articuloServices.crear(articulo2);

        Set<Articulo> articuloList = new HashSet<>();
        articuloList.add(articulo);
        articuloList.add(articulo2);

        Suplidor suplidor = new Suplidor("Alonso", articuloList);
        SuplidorServices suplidorServices = (SuplidorServices) context.getBean("suplidorServices");
        suplidorServices.crear(suplidor);

        System.out.println("Articulo guardado y supidor");
    }

}
