package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Camion;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;

import static com.demo.FactoryMethodLogisticaTransporte.logistica.RutaEntrega.inicializarGraphHopper;


public class LogisticaTierra extends Logistica {
    public double distancia;
    public String tiempoFinal;
    RutaEntrega rutaEntrega = new RutaEntrega();
    @Override
    public void planEntrega(String origen, String destino) {
        // Implementación específica para logística terrestre

        inicializarGraphHopper("car");

        distancia = rutaEntrega.calcularDistancia(origen, destino);
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte terrestre
        return new Camion();
    }
    // Otros métodos y atributos específicos de la logística terrestre

    public String distanciaToString(){
        return "Distancia a destino: " + (distancia / 1000) + " km";
    }

    public String tiempoToString(){
        tiempoFinal = rutaEntrega.tiempoToString();

        return tiempoFinal;
    }
}