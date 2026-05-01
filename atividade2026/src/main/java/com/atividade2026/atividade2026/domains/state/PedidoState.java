package com.atividade2026.atividade2026.domains.state;

import com.atividade2026.atividade2026.domains.Pedido;

public interface PedidoState {

    void pagar(Pedido pedido);

    void cancelar(Pedido pedido);

    void enviar(Pedido pedido);

    String getNome();
}
