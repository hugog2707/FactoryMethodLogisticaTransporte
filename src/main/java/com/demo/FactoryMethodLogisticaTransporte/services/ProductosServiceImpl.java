package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Productos;
import com.demo.FactoryMethodLogisticaTransporte.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService {
    List<Productos> productos = null;
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
    public Productos obtenerProductoPorNombre(String nombre) {

        productos = obtenerProductos();

        for (Productos p : productos) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
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
