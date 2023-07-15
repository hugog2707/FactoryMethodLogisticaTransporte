package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Carro;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

public class LogisticaTierra extends Logistica {
    @Override
    public void planEntrega() {
        // Implementación específica para logística terrestre
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte terrestre
        return new Carro();
    }
    // Otros métodos y atributos específicos de la logística terrestre
}