package com.emergente.mongo.controladores;

import com.emergente.mongo.repositorios.ArticuloRepository;
import com.emergente.mongo.servicios.ArticuloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RutasController {

    private final ArticuloServices articuloServices;

    public RutasController(ArticuloServices articuloServices) {
        this.articuloServices = articuloServices;
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

    @PostMapping("/nuevoArticulo")
    public String nuevoArticulo() {
        return "";
    }

}
