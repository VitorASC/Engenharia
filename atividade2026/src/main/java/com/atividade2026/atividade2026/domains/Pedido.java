package com.atividade2026.atividade2026.domains;

import com.atividade2026.atividade2026.domains.state.*;
import com.atividade2026.atividade2026.strategy.FreteStrategy;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private BigDecimal valor;

    private String tipoFrete;

    private String status;

    @Transient
    private PedidoState estadoAtual;

    @Transient
    private FreteStrategy freteStrategy;

    public Pedido() {
        this.status = "AGUARDANDO_PAGAMENTO";
        this.estadoAtual = new AguardandoState();
    }

    public Pedido(Integer id, String descricao, BigDecimal valor, String tipoFrete) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoFrete = tipoFrete;
        this.status = "AGUARDANDO_PAGAMENTO";
        this.estadoAtual = new AguardandoState();
    }

    @PostLoad
    public void reconstruirState() {
        if (this.status == null) {
            this.status = "AGUARDANDO_PAGAMENTO";
        }
        switch (this.status) {
            case "PAGO":
                this.estadoAtual = new PagoState();
                break;
            case "ENVIADO":
                this.estadoAtual = new EnviadoState();
                break;
            case "CANCELADO":
                this.estadoAtual = new CanceladoState();
                break;
            default:
                this.estadoAtual = new AguardandoState();
                break;
        }
    }

    public void pagar() {
        estadoAtual.pagar(this);
    }

    public void cancelar() {
        estadoAtual.cancelar(this);
    }

    public void enviar() {
        estadoAtual.enviar(this);
    }

    public double calcularFrete() {
        if (freteStrategy == null) {
            throw new RuntimeException("Estratégia de frete não definida");
        }
        return freteStrategy.calcular(valor.doubleValue());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PedidoState getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(PedidoState estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public void setFreteStrategy(FreteStrategy freteStrategy) {
        this.freteStrategy = freteStrategy;
    }

    public FreteStrategy getFreteStrategy() {
        return freteStrategy;
    }
}
