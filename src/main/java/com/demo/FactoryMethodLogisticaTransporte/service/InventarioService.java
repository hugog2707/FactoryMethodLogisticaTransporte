package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Inventario;
import com.demo.FactoryMethodLogisticaTransporte.models.Productos;

public interface InventarioService {
    Inventario GuardarInventario(Inventario i);
    Inventario obtenerInventario(Long id);
    void agregarProductoAlInventario(Inventario i, Productos p);
    public void eliminarProducto(Inventario i, Productos p);
}
