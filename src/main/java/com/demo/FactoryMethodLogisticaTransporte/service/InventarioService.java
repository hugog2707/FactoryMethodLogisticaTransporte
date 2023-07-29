package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Inventario;
import com.demo.FactoryMethodLogisticaTransporte.models.Productos;

import java.util.List;

public interface InventarioService {
    Inventario GuardarInventario(Inventario i);
    List<Inventario> obtenerInventario();
    Inventario obtenerInventarioPorId(Long id);
    void agregarProductoAlInventario(Inventario i, Productos p);
    public void eliminarProducto(Inventario i, Productos p);
}
