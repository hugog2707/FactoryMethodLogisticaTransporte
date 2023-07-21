package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Carro;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

public class LogisticaTierra extends Logistica {
    @Override
    public void planEntrega() {
        // Implementación específica para logística terrestre
        // ruta de entrega
        RutaEntrega rutaEntrega = new RutaEntrega();
        rutaEntrega.setOrigen("4.710989,-74.072092");
        rutaEntrega.setDestino("6.244203,-75.581211");
        rutaEntrega.inicializarGraphHopper();
        double distancia = rutaEntrega.calcularDistancia();
        System.out.println("Plan de entrega por transporte terrestre");
        System.out.println("Distancia entre Bogotá y Medellín: " + distancia + " km");
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte terrestre
        return new Carro();
    }
    // Otros métodos y atributos específicos de la logística terrestre
}