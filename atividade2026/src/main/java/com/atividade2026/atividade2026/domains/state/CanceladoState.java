package com.atividade2026.atividade2026.domains.state;

import com.atividade2026.atividade2026.domains.Pedido;

public class CanceladoState implements PedidoState {

    @Override
    public void pagar(Pedido pedido) {
        throw new RuntimeException("Pedido cancelado não pode ser alterado");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new RuntimeException("Pedido já está cancelado");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new RuntimeException("Pedido cancelado não pode ser enviado");
    }

    @Override
    public String getNome() {
        return "CANCELADO";
    }
}
