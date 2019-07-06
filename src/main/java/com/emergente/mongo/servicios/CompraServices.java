package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Compra;
import com.emergente.mongo.repositorios.CompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraServices {

    private final CompraRepository compraRepository;

    public CompraServices(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Transactional
    public void crear(Compra compra) {
        compraRepository.save(compra);
    }
}
