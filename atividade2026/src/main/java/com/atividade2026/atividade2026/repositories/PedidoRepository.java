package com.atividade2026.atividade2026.repositories;

import com.atividade2026.atividade2026.domains.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
