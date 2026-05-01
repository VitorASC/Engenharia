package com.atividade2026.atividade2026.strategy;

public class FreteTerrestreStrategy implements FreteStrategy {

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * 0.05;
    }

    @Override
    public String getTipo() {
        return "TERRESTRE";
    }
}
