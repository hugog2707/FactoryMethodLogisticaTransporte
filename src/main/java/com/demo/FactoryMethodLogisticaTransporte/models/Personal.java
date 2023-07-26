package com.demo.FactoryMethodLogisticaTransporte.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idPersonal;

    @Column(unique = true)
    public String nombre;
    @Column(unique = true)
    public String numeroDocumento;
    @Column(unique = true)

    public String correo;
    public String tipoDocumento;
    public String direccion;
    public String telefono;
    public String estado;
    public String fechaIngreso;
    public String fechaSalida;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    public Cargo cargoPersonal;
}
