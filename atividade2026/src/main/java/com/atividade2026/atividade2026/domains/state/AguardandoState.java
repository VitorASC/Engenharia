package com.atividade2026.atividade2026.domains.state;

import com.atividade2026.atividade2026.domains.Pedido;

public class AguardandoState implements PedidoState {

    @Override
    public void pagar(Pedido pedido) {
        pedido.setEstadoAtual(new PagoState());
        pedido.setStatus("PAGO");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstadoAtual(new CanceladoState());
        pedido.setStatus("CANCELADO");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new RuntimeException("Pedido precisa ser pago antes de enviar");
    }

    @Override
    public String getNome() {
        return "AGUARDANDO_PAGAMENTO";
    }
}
