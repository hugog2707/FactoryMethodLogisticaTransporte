package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.Productos;
import com.demo.FactoryMethodLogisticaTransporte.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService{
    @Autowired
    public ProductosRepository productosRepository;
    @Override
    public Productos GuardarProducto(Productos p) {
        return productosRepository.save(p);
    }

    @Override
    public Productos obtenerProductoPorId(Long id) {
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public List<Productos> obtenerProductos() {
        return productosRepository.findAll();
    }

    @Override
    public String eliminarProducto(Long id) {
        productosRepository.deleteById(id);
        return "Producto eliminado";
    }
}
