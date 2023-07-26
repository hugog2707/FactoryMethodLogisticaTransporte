package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;

public interface CargoService {
    Cargo GuardarCargo(Cargo c);
    Cargo obtenerCargo(Long id);
}
