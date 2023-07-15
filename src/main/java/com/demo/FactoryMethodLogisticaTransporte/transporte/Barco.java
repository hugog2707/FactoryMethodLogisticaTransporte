package com.demo.FactoryMethodLogisticaTransporte.transporte;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Barco implements Transporte {
    public int id;
    public String nombre;
    public String tipo;
    public String color;
    public String marca;

    @Override
    public void entrega() {
        System.out.println("Entrega por transporte marítimo (barco)");
        // Implementación específica para entrega por barco
    }
    // Otros métodos y atributos específicos de la entrega por barco

}
