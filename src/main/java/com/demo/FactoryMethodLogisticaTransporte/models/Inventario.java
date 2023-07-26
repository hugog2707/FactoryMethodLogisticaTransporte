package com.demo.FactoryMethodLogisticaTransporte.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "tipo_inventario_id")
    public TipoInventario tipoInventario;

    @JsonIgnore
    @OneToMany(mappedBy = "inventario")
    public List<Productos> productos;

    @OneToOne
    @JoinColumn(name = "id_personal")
    public Personal encargado;

    public void agregarProducto(Productos producto){
        this.productos.add(producto);
        producto.setInventario(this);
    }

    public void eliminarProducto(Productos producto){
        this.productos.remove(producto);
        producto.setInventario(null);
    }
}
