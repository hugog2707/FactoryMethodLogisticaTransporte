package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Carro;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

import static com.demo.FactoryMethodLogisticaTransporte.logistica.RutaEntrega.inicializarGraphHopper;


public class LogisticaTierra extends Logistica {
    public double distancia;
    @Override
    public void planEntrega(String origen, String destino) {
        // Implementación específica para logística terrestre
        inicializarGraphHopper("car");

        distancia = calcularDistancia(origen, destino);
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte terrestre
        return new Carro();
    }
    // Otros métodos y atributos específicos de la logística terrestre
    public double calcularDistancia(String origen, String destino) {
        RutaEntrega rutaEntrega = new RutaEntrega();
        return rutaEntrega.calcularDistancia(origen, destino);
    }

    public String distanciaToString() {
        return "Distancia entre Bogotá y Medellín: " + distancia + " km";
    }
}