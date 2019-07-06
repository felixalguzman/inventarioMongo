package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Venta;
import com.emergente.mongo.repositorios.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServices {

    private final VentaRepository ventaRepository;

    public VentaServices(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional
    public void crear(Venta venta){
        ventaRepository.save(venta);
    }
}
