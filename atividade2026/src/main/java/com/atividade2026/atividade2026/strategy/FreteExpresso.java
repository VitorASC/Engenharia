package com.atividade2026.atividade2026.strategy;

public class FreteExpresso implements FreteStrategy {

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * 0.10;
    }

    @Override
    public String getTipo() {
        return "EXPRESSO";
    }
}
