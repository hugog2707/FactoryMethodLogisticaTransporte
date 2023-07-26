package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Productos;

import java.util.List;

public interface ProductosService {
    Productos GuardarProducto(Productos p);
    Productos obtenerProductoPorId(Long id);
    List<Productos> obtenerProductos();
    String eliminarProducto(Long id);
}
