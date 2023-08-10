package com.demo.FactoryMethodLogisticaTransporte.repositories;

import com.demo.FactoryMethodLogisticaTransporte.models.TipoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInventarioRepository extends JpaRepository<TipoInventario, Long> {
}
