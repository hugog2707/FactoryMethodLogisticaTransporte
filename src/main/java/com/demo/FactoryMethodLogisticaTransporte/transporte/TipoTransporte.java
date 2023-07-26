package com.demo.FactoryMethodLogisticaTransporte.transporte;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TipoTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idTipoTransporte;
    public String nombre;
}
