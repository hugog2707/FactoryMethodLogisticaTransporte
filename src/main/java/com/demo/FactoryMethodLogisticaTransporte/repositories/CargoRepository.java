package com.demo.FactoryMethodLogisticaTransporte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
