package com.demo.FactoryMethodLogisticaTransporte.models;

import com.demo.FactoryMethodLogisticaTransporte.transporte.TipoTransporte;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idPedido;
    public String descripcion;
    public Long peso;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    public Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    public Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_personal")
    public Personal encargado;
}
