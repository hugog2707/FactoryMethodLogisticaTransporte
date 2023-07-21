package com.demo.FactoryMethodLogisticaTransporte.logistica;

import lombok.Data;

@Data
public class RutaEntrega {
    private String origen;
    private String destino;
    private long distancia;
    private long tiempo;
}
