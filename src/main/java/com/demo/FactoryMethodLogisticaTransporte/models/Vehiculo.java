package com.demo.FactoryMethodLogisticaTransporte.models;

import com.demo.FactoryMethodLogisticaTransporte.transporte.TipoTransporte;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int idVehiculo;
    public String nombre;
    public String tipo;
    public String color;
    public String marca;
    public int capacidad;

    @OneToOne
    @JoinColumn(name = "id_tipo_transporte")
    public TipoTransporte tipoTransporte;
}
