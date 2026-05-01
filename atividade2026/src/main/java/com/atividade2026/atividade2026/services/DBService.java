package com.atividade2026.atividade2026.services;

import com.atividade2026.atividade2026.domains.Pedido;
import com.atividade2026.atividade2026.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DBService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void initDB() {

        Pedido p1 = new Pedido(
                null,
                "Smartphone Samsung Galaxy S24",
                new BigDecimal("3500.00"),
                "TERRESTRE"
        );
        Pedido p2 = new Pedido(
                null,
                "Notebook Dell Inspiron 15",
                new BigDecimal("4200.00"),
                "AEREO"
        );
        Pedido p3 = new Pedido(
                null,
                "Fone de Ouvido JBL Tune 520BT",
                new BigDecimal("250.00"),
                "TERRESTRE"
        );
        Pedido p4 = new Pedido(
                null,
                "Mouse Gamer Logitech G502",
                new BigDecimal("350.00"),
                "AEREO"
        );
        Pedido p5 = new Pedido(
                null,
                "Teclado Mecânico Redragon Kumara",
                new BigDecimal("280.00"),
                "TERRESTRE"
        );

        pedidoRepository.save(p1);
        pedidoRepository.save(p2);
        pedidoRepository.save(p3);
        pedidoRepository.save(p4);
        pedidoRepository.save(p5);
    }
}
