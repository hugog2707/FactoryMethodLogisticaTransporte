package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idPersonal;

    public String nombre;
    public String tipoDocumento;
    public String numeroDocumento;
    public String direccion;
    public String telefono;
    public String correo;
    public String cargo;
    public String estado;
    public String fechaIngreso;
    public String fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    public Cargo cargoPersonal;
}
