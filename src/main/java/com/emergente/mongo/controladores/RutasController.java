package com.emergente.mongo.controladores;

import com.emergente.mongo.entidades.*;
import com.emergente.mongo.servicios.ArticuloServices;
import com.emergente.mongo.servicios.CompraServices;
import com.emergente.mongo.servicios.SuplidorServices;
import com.emergente.mongo.servicios.VentaServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CompraServices compraServices;
    private final VentaServices ventaServices;

    public RutasController(ArticuloServices articuloServices, SuplidorServices suplidorServices, CompraServices compraServices, VentaServices ventaServices) {
        this.articuloServices = articuloServices;
        this.suplidorServices = suplidorServices;
        this.compraServices = compraServices;
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

    @GetMapping("/compra")
    public String compra(Model model) {
        model.addAttribute("suplidores", suplidorServices.getAll());
        return "compra";
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

    @RequestMapping(value = "/comprar", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity comprar(@RequestBody List<CompraWrapper> list) {

        Set<DetalleCompra> detalleCompras = new HashSet<>();
        for (CompraWrapper wrapper : list) {
            System.out.println("compras" + wrapper);
            Articulo articulo = articuloServices.buscarPorId(wrapper.getArticulo());
            DetalleCompra detalleCompra = new DetalleCompra(articulo, wrapper.getCantidad(), articulo.getPrecio() * wrapper.getCantidad());
            detalleCompras.add(detalleCompra);
            articuloServices.actualizarStockCompra(articulo, wrapper.getCantidad());

        }
        ObjectId id = new ObjectId(list.get(0).getSuplidor());
        Compra compra = new Compra(detalleCompras, LocalDate.now(), suplidorServices.buscarPorId(id));
        compraServices.crear(compra);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/nuevoArticulo")
    public String nuevoArticulo() {
        return "";
    }

}
