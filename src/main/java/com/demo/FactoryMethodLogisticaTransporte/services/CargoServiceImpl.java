package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;
import com.demo.FactoryMethodLogisticaTransporte.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService{
    @Autowired
    private CargoRepository cargoRepository;
    private List<Cargo> cargos;
    @Override
    public Cargo GuardarCargo(Cargo c) {
        return cargoRepository.save(c);
    }

    @Override
    public Cargo obtenerCargoPorId(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @Override
    public Cargo obtenerCargoPorNombre(String nombre) {
        cargos = cargoRepository.findAll();
        for (Cargo c: cargos) {
            if (c.getNombre().equals(nombre)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cargo> obtenerTodosLosCargos() {
        return cargoRepository.findAll();
    }
}
