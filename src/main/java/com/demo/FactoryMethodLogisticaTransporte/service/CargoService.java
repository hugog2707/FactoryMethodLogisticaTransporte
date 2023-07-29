package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;

import java.util.List;

public interface CargoService {
    Cargo GuardarCargo(Cargo c);
    Cargo obtenerCargoPorId(Long id);
    Cargo obtenerCargoPorNombre(String nombre);
    List<Cargo> obtenerTodosLosCargos();
}
