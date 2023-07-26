package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;
import com.demo.FactoryMethodLogisticaTransporte.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoServiceImpl implements CargoService{
    @Autowired
    private CargoRepository cargoRepository;
    @Override
    public Cargo GuardarCargo(Cargo c) {
        return cargoRepository.save(c);
    }

    @Override
    public Cargo obtenerCargo(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }
}
