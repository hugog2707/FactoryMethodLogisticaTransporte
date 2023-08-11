package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Inventario;
import com.demo.FactoryMethodLogisticaTransporte.models.Productos;
import com.demo.FactoryMethodLogisticaTransporte.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public Inventario GuardarInventario(Inventario i) {
        return inventarioRepository.save(i);
    }

    @Override
    public List<Inventario> obtenerInventario() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    @Override
    public void agregarProductoAlInventario(Inventario i, Productos p) {
        i.agregarProducto(p);
        GuardarInventario(i);
    }

    @Override
    public void eliminarProducto(Inventario i, Productos p) {
        i.eliminarProducto(p);
        GuardarInventario(i);
    }
}
