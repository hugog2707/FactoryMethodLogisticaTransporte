package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Productos;

import java.util.List;

public interface ProductosService {
    Productos GuardarProducto(Productos p);
    Productos obtenerProductoPorId(Long id);
    Productos obtenerProductoPorNombre(String nombre);
    List<Productos> obtenerProductos();
    String eliminarProducto(Long id);
}
