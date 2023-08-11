package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.HistorialProductos;

import java.util.List;

public interface HistorialProductosService {
    List<HistorialProductos> getHistorialProductos();
    List<HistorialProductos> getHistorialProductosByFecha(String fecha);
}
