package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.HistorialProductos;
import com.demo.FactoryMethodLogisticaTransporte.repositories.HistorialProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HistorialProductosServiceImpl implements HistorialProductosService{
    @Autowired
    private HistorialProductosRepository historialProductosRepository;
    @Override
    public List<HistorialProductos> getHistorialProductos() {
        return historialProductosRepository.findAll();
    }

    @Override
    public List<HistorialProductos> getHistorialProductosByFecha(String fecha) {
        List<HistorialProductos> historialProductos = historialProductosRepository.findAll();
        for (HistorialProductos historialProducto : historialProductos) {
            if (historialProducto.getFecha().equals(fecha)) {
                return historialProductos;
            }
        }
        return null;
    }
}
