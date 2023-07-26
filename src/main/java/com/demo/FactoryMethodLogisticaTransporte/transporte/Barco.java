package com.demo.FactoryMethodLogisticaTransporte.transporte;

import com.demo.FactoryMethodLogisticaTransporte.models.Personal;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("barco")
public class Barco implements Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idBarco;

    public String nombre;
    public String tipo;
    public String color;
    public String marca;

    @OneToOne
    @JoinColumn(name = "id_tipo_transporte")
    public TipoTransporte tipoTransporte;

    @Override
    public void entrega() {
        System.out.println("Entrega por transporte marítimo (barco)");
        // Implementación específica para entrega por barco
    }
    // Otros métodos y atributos específicos de la entrega por barco

}
