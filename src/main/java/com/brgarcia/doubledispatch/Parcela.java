package com.brgarcia.doubledispatch;

import java.math.BigDecimal;

public class Parcela {

    private BigDecimal valor;

    public Parcela(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}