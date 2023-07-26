package com.demo.FactoryMethodLogisticaTransporte.repository;

import com.demo.FactoryMethodLogisticaTransporte.models.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
