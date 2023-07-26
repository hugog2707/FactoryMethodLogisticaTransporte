package com.demo.FactoryMethodLogisticaTransporte.repository;

import com.demo.FactoryMethodLogisticaTransporte.models.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
