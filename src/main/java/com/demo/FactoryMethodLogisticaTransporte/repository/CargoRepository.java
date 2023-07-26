package com.demo.FactoryMethodLogisticaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.FactoryMethodLogisticaTransporte.models.Cargo;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
