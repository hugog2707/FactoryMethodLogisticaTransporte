package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.demo.FactoryMethodLogisticaTransporte.transporte.Carro;
import com.demo.FactoryMethodLogisticaTransporte.transporte.Transporte;
import com.google.maps.DistanceMatrixApi;

public class LogisticaTierra extends Logistica {
    public DistanceMatrixApi distanceMatrixApi;
    @Override
    public void planEntrega() {
        // Implementación específica para logística terrestre
        // ruta de entrega
        RutaEntrega rutaEntrega = crearRutaEntrega("bogota", "medellin");
        System.out.println("Plan de entrega por transporte terrestre");
        System.out.println("Origen: " + rutaEntrega.getOrigen());
        System.out.println("Destino: " + rutaEntrega.getDestino());
        System.out.println("Distancia: " + rutaEntrega.getDistancia());
        System.out.println("Tiempo estimado: " + rutaEntrega.getTiempo());
    }

    @Override
    public Transporte fabrica() {
        // Implementación específica para crear transporte terrestre
        return new Carro();
    }
    // Otros métodos y atributos específicos de la logística terrestre

    private RutaEntrega crearRutaEntrega(String origen, String destino) {
        // Calcula la ruta de entrega entre el origen y el destino con google maps
        RutaEntrega rutaEntrega = new RutaEntrega();
        rutaEntrega.setOrigen(origen);
        rutaEntrega.setDestino(destino);
        rutaEntrega.setDistancia(400);
        rutaEntrega.setTiempo(10);
        return rutaEntrega;
    }
}