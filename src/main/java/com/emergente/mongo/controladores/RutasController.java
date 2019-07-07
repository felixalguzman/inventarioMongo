package com.emergente.mongo.controladores;

import com.emergente.mongo.entidades.Articulo;
import com.emergente.mongo.entidades.CompraWrapper;
import com.emergente.mongo.entidades.DetalleCompra;
import com.emergente.mongo.entidades.Suplidor;
import com.emergente.mongo.servicios.ArticuloServices;
import com.emergente.mongo.servicios.SuplidorServices;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RutasController {

    private final ArticuloServices articuloServices;
    private final SuplidorServices suplidorServices;

    public RutasController(ArticuloServices articuloServices, SuplidorServices suplidorServices) {
        this.articuloServices = articuloServices;
        this.suplidorServices = suplidorServices;
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
    public String venta() {
        return "venta";
    }

    @RequestMapping(value = "/comprar", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity comprar(@RequestBody List<CompraWrapper> list) {

        List<DetalleCompra> detalleCompras = new ArrayList<>();
        for (CompraWrapper wrapper : list) {
            System.out.println("compras" + wrapper);
            Articulo articulo = articuloServices.buscarPorId(wrapper.getArticulo());
            DetalleCompra detalleCompra = new DetalleCompra(articulo, wrapper.getCantidad(), articulo.getPrecio());
            detalleCompras.add(detalleCompra);

        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/nuevoArticulo")
    public String nuevoArticulo() {
        return "";
    }

}
