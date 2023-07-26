package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TipoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int tipoInventarioId;

    @Column(unique = true)
    public String nombre;

    public String descripcion;
}
