package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idInventario;

    @ManyToOne
    @JoinColumn(name = "tipo_inventarioId")
    public TipoInventario tipoInventario;

    @OneToMany(mappedBy = "inventario")
    public List<Productos> productos;

    @OneToOne
    @JoinColumn(name = "id_personal")
    public Personal encargado;
}
