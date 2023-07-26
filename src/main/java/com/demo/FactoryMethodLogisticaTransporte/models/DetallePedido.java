package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idDetallePedido;

    public String origen;
    public String destino;
    public String fechaEntrega;
    public String estado;
    public String fechaEntregado;
    public String fechaPedido;
    public String fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    public Pedido idPedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    public Productos idProducto;
}
