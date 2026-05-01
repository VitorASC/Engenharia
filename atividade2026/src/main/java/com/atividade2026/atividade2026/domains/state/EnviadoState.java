package com.atividade2026.atividade2026.domains.state;

import com.atividade2026.atividade2026.domains.Pedido;

public class EnviadoState implements PedidoState {

    @Override
    public void pagar(Pedido pedido) {
        throw new RuntimeException("Pedido já foi enviado");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new RuntimeException("Pedido já foi enviado, não pode ser cancelado");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new RuntimeException("Pedido já foi enviado");
    }

    @Override
    public String getNome() {
        return "ENVIADO";
    }
}
