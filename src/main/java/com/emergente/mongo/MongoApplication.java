package com.emergente.mongo;

import com.emergente.mongo.entidades.*;
import com.emergente.mongo.repositorios.ArticuloRepository;
import com.emergente.mongo.servicios.ArticuloServices;
import com.emergente.mongo.servicios.CompraServices;
import com.emergente.mongo.servicios.SuplidorServices;
import com.emergente.mongo.servicios.VentaServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
//		SpringApplication.run(MongoApplication.class, args);

        ApplicationContext context = SpringApplication.run(MongoApplication.class, args);

        ArticuloServices articuloServices = (ArticuloServices) context.getBean("articuloServices");


        Articulo articulo = new Articulo("Cosa 1", "una cosa", 5, 12);
        Articulo articulo2 = new Articulo("Cosa 2", "dos cosa", 6, 18);
//        articuloServices.crear(articulo);
//        articuloServices.crear(articulo2);
//
//
//
//        SuplidorDetalle suplidorDetalle = new SuplidorDetalle(5, 6, articuloServices.buscarPorNombre("Cosa 1"));
//        SuplidorDetalle suplidorDetalle1 = new SuplidorDetalle(7, 4, articuloServices.buscarPorNombre("Cosa 2"));
//
//        Set<SuplidorDetalle> detalles = new HashSet<>();
//        detalles.add(suplidorDetalle);
//        detalles.add(suplidorDetalle1);
//
//
//        Suplidor suplidor = new Suplidor("Alonso", detalles);
//        SuplidorServices suplidorServices = (SuplidorServices) context.getBean("suplidorServices");
//        suplidorServices.crear(suplidor);

//        Suplidor suplidor = suplidorServices.buscarPorNombre("Alonso");
//        Set<DetalleCompra> detalleCompras = new HashSet<>();
//        for (SuplidorDetalle detalle : suplidor.getSuplidorDetalles()) {

//            DetalleCompra detalleCompra = new DetalleCompra(detalle.getArticulo(), 5, 190);
//            detalleCompras.add(detalleCompra);

//            articuloServices.actualizarStockCompra(detalle.getArticulo(), 5);
//        }


//        Compra compra = new Compra(detalleCompras, LocalDate.now(), suplidor);
//        CompraServices compraServices = (CompraServices)context.getBean("compraServices");
//        compraServices.crear(compra);

        DetalleVenta detalleVenta = new DetalleVenta(articuloServices.buscarPorNombre("Cosa 1"), 3);
        DetalleVenta detalleVenta2 = new DetalleVenta(articuloServices.buscarPorNombre("Cosa 2"), 4);


        Set<DetalleVenta> ventas = new HashSet<>();
        ventas.add(detalleVenta);
        ventas.add(detalleVenta2);

        for (DetalleVenta venta : ventas) {
//            articuloServices.actualizarStockVenta(venta.getArticulo(), venta.getCantidad());

        }

        Venta venta = new Venta("Felix", ventas, LocalDate.now());
        VentaServices ventaServices = (VentaServices) context.getBean("ventaServices");
//        ventaServices.crear(venta);


        System.out.println("Articulo guardado y supidor");
    }

}
