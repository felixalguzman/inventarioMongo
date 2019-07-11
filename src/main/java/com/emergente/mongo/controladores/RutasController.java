package com.emergente.mongo.controladores;

import com.emergente.mongo.entidades.*;
import com.emergente.mongo.servicios.ArticuloServices;
import com.emergente.mongo.servicios.MovimientoServices;
import com.emergente.mongo.servicios.SuplidorServices;
import com.emergente.mongo.servicios.VentaServices;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class RutasController {

    private final ArticuloServices articuloServices;
    private final SuplidorServices suplidorServices;
    private final MovimientoServices movimientoServices;
    private final VentaServices ventaServices;

    public RutasController(ArticuloServices articuloServices, SuplidorServices suplidorServices, MovimientoServices movimientoServices, VentaServices ventaServices) {
        this.articuloServices = articuloServices;
        this.suplidorServices = suplidorServices;
        this.movimientoServices = movimientoServices;
        this.ventaServices = ventaServices;
    }

    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping("/articulos")
    public String articulos(Model model) {
        model.addAttribute("articulos", articuloServices.getAll());
        return "articulos";
    }

    @GetMapping("/movimiento")
    public String compra(Model model) {
        model.addAttribute("articulos", articuloServices.getAll());

        return "movimiento";
    }

    @RequestMapping(value = "/articulosSuplidor/{suplidorId}", method = RequestMethod.POST)
    public ResponseEntity<List<Articulo>> buscarArticulosSuplidor(@PathVariable(value = "suplidorId") ObjectId id) {
        System.out.println("id:  " + id);

        List<Articulo> articuloList = new ArrayList<>();
        for (String objectId : suplidorServices.findArticulosBySuplidorId(id)) {


            Articulo articulo = articuloServices.buscarPorId(objectId);
            System.out.println("id " + id);
            if (articulo != null) {
                articuloList.add(articulo);
            }
        }

        return new ResponseEntity<>(articuloList, HttpStatus.OK);
    }

    @PostMapping("/articulo/{articuloId}")
    public ResponseEntity<Articulo> buscarArticulo(@PathVariable(value = "articuloId") String id) {

        return new ResponseEntity<>(articuloServices.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping("/suplidor/{suplidorId}")
    public ResponseEntity<Suplidor> buscarSuplidor(@PathVariable(value = "suplidorId") ObjectId id) {

        return new ResponseEntity<>(suplidorServices.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/venta")
    public String venta(Model model) {
        model.addAttribute("articulos", articuloServices.getAll());
        return "venta";
    }

    @GetMapping("/generarOrden")
    public String orden(Model model) {
        model.addAttribute("articulos", articuloServices.getAll());
        return "orden";
    }

    @PostMapping(value = "/vender/{nombreCliente}", consumes = "application/json")
    public ResponseEntity vender(@RequestBody List<VentaWrapper> ventas, @PathVariable(value = "nombreCliente") String nombre) {

        Set<DetalleVenta> detalleVentas = new HashSet<>();
        System.out.println("Cliente: " + nombre);
        for (VentaWrapper v : ventas) {
            Articulo articulo = articuloServices.buscarPorId(v.getArticulo());
            DetalleVenta detalleVenta = new DetalleVenta(articulo, v.getCantidad());
            detalleVentas.add(detalleVenta);
            articuloServices.actualizarStockVenta(articulo, v.getCantidad());

            System.out.println("art: " + v.getArticulo() + " cant: " + v.getCantidad());
        }
        Venta venta = new Venta(nombre, detalleVentas, LocalDate.now());
        ventaServices.crear(venta);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/movimiento", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity comprar(@RequestBody MovimientoWrapper movimientoWrapper) {

        Set<DetalleMovimiento> detalleMovimientos = new HashSet<>();

        for (Articulos articulos : movimientoWrapper.getArticulos()) {

            Articulo articulo = articuloServices.buscarPorId(articulos.getArticulo());
            DetalleMovimiento detalleMovimiento = new DetalleMovimiento(articulo, articulos.getCantidad());
            detalleMovimientos.add(detalleMovimiento);
        }

        Movimiento movimiento = new Movimiento(detalleMovimientos, LocalDate.now(), movimientoWrapper.getTipo() == 0 ? TipoMovimiento.ENTRADA : TipoMovimiento.SALIDA);
        movimientoServices.crear(movimiento);

        movimientoServices.buscarAverage();

        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @RequestMapping(value = "/generarOrdenCompra", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity generarOrdenCompra(@RequestBody List<MovimientoWrapper> list) {

        MatchOperation matchStage = Aggregation.match(new Criteria("foo").is("bar"));
//        Aggregation aggregation
//                = Aggregation.newAggregation(matchStage, projectStage);

//        AggregationResults<OutType> output = mongoTemplate.aggregate(aggregation, "foobar", OutType.class);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/nuevoArticulo")
    public String nuevoArticulo() {
        return "";
    }

}
