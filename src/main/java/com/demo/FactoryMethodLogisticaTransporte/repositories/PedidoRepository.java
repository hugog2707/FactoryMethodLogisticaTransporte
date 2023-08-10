package com.demo.FactoryMethodLogisticaTransporte.repositories;

import com.demo.FactoryMethodLogisticaTransporte.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
