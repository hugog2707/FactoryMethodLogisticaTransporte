package com.demo.FactoryMethodLogisticaTransporte.transporte;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Barco {
    public int id;
    public String nombre;
    public String tipo;
    public String color;
    public String marca;

}