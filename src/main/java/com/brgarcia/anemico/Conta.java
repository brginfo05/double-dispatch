package com.brgarcia.anemico;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Conta {

    private BigDecimal valor;
    private List<Parcela> parcelas;

    public Conta(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
}
