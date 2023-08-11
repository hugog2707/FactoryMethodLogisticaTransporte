package com.demo.FactoryMethodLogisticaTransporte.services;

import com.demo.FactoryMethodLogisticaTransporte.models.Personal;

import java.util.List;

public interface PersonalService {
    Personal GuardarPersonal(Personal p);
    Personal obtenerPersonalPorId(Long id);
    List<Personal> obtenerPersonal();
}
