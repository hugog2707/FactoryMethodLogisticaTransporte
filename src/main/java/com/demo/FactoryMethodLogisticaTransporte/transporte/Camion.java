package com.demo.FactoryMethodLogisticaTransporte.transporte;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("carro")
public class Camion implements Transporte{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idCarro;

    public String nombre;
    public String tipo;
    public String color;
    public String marca;
    public int capacidad;

    @OneToOne
    @JoinColumn(name = "id_tipo_transporte")
    public TipoTransporte tipoTransporte;

    @Override
    public void entrega() {
        System.out.println("Entrega por transporte terrestre (carro)");
        // Implementación específica para entrega por carro
    }
    // Otros métodos y atributos específicos de la entrega por carro
}
