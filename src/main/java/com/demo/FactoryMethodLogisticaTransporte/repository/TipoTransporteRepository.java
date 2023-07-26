package com.demo.FactoryMethodLogisticaTransporte.repository;

import com.demo.FactoryMethodLogisticaTransporte.transporte.TipoTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransporteRepository extends JpaRepository<TipoTransporte, Long> {
}
