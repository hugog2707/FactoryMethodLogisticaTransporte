package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.TipoInventario;

import java.util.List;

public interface TipoInventarioService {
    List<TipoInventario> obtenerTipoInventario();
    TipoInventario GuardarTipoInventario(TipoInventario ti);
}
