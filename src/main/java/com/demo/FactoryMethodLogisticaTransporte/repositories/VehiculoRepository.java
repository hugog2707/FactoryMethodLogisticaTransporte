package com.demo.FactoryMethodLogisticaTransporte.repositories;

import com.demo.FactoryMethodLogisticaTransporte.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
