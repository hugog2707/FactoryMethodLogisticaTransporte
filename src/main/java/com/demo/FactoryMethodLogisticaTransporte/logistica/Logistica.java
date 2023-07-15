package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

public abstract class Logistica {
    public abstract void planEntrega();
    public abstract Transporte fabrica();
    // Otros métodos y atributos comunes a la logística
}
