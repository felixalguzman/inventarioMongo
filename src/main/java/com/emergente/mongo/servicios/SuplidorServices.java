package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Suplidor;
import com.emergente.mongo.repositorios.SuplidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuplidorServices {

    private final SuplidorRepository suplidorRepository;

    public SuplidorServices(SuplidorRepository suplidorRepository) {
        this.suplidorRepository = suplidorRepository;
    }

    @Transactional
    public void crear(Suplidor suplidor){
        suplidorRepository.save(suplidor);
    }
}
