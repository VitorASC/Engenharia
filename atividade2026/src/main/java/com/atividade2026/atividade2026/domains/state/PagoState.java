package com.atividade2026.atividade2026.domains.state;

import com.atividade2026.atividade2026.domains.Pedido;

public class PagoState implements PedidoState {

    @Override
    public void pagar(Pedido pedido) {
        throw new RuntimeException("Pedido já foi pago, não pode ser pago novamente");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstadoAtual(new CanceladoState());
        pedido.setStatus("CANCELADO");
    }

    @Override
    public void enviar(Pedido pedido) {
        pedido.setEstadoAtual(new EnviadoState());
        pedido.setStatus("ENVIADO");
    }

    @Override
    public String getNome() {
        return "PAGO";
    }
}
