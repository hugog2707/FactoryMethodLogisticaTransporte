package com.demo.FactoryMethodLogisticaTransporte.service;

import com.demo.FactoryMethodLogisticaTransporte.models.TipoInventario;

import java.util.List;

public interface TipoInventarioService {
    List<TipoInventario> obtenerTipoInventario();
    TipoInventario GuardarTipoInventario(TipoInventario ti);
}
