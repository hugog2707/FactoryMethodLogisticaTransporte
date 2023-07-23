package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Barco;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

public class LogisticaMar extends Logistica {
    @Override
    public void planEntrega(String origen, String destino) {
        // Implementación específica para logística marítima
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte marítimo
        return new Barco();
    }
    // Otros métodos y atributos específicos de la logística marítima
}
