package com.demo.FactoryMethodLogisticaTransporte.transporte;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Carro implements Transporte{
    public int id;
    public String nombre;
    public String tipo;
    public String color;
    public String marca;

    @Override
    public void entrega() {
        System.out.println("Entrega por transporte terrestre (carro)");
        // Implementación específica para entrega por carro
    }
    // Otros métodos y atributos específicos de la entrega por carro
}
