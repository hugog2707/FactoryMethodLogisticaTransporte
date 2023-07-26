package com.demo.FactoryMethodLogisticaTransporte.repository;

import com.demo.FactoryMethodLogisticaTransporte.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
