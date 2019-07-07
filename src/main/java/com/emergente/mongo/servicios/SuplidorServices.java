package com.emergente.mongo.servicios;

import com.emergente.mongo.entidades.Suplidor;
import com.emergente.mongo.entidades.SuplidorDetalle;
import com.emergente.mongo.repositorios.SuplidorRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuplidorServices {

    private final SuplidorRepository suplidorRepository;

    public SuplidorServices(SuplidorRepository suplidorRepository) {
        this.suplidorRepository = suplidorRepository;
    }

    @Transactional
    public void crear(Suplidor suplidor) {
        suplidorRepository.save(suplidor);
    }

    public Suplidor buscarPorNombre(String nombre) {
        return suplidorRepository.findByNombre(nombre);
    }

    public List<Suplidor> getAll() {
        return suplidorRepository.findAll();
    }

    public List<String> findArticulosBySuplidorId(ObjectId id) {
        Suplidor suplidor = suplidorRepository.findById(id).orElse(null);

        List<String> list = new ArrayList<>();
        if (suplidor != null) {

            for (SuplidorDetalle detalle : suplidor.getSuplidorDetalles()) {

                list.add(detalle.getArticulo().get_id());

            }

        }

        return list;
    }

    public Suplidor buscarPorId(ObjectId id){
        return suplidorRepository.findById(id).orElse(null);
    }
}
