package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idCargo;

    @Column(unique = true)
    public String nombre;
}
