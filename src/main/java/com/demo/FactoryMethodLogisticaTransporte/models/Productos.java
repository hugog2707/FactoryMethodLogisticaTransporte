package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idProducto;

    public String nombre;
    public String tipo;
    public String color;
    public Long peso;

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    public Inventario inventario;
}
