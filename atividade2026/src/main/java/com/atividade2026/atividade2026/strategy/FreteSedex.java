package com.atividade2026.atividade2026.strategy;

public class FreteSedex implements FreteStrategy {

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * 0.15;
    }

    @Override
    public String getTipo() {
        return "SEDEX";
    }
}
