package com.brgarcia.doubledispatch;

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

    public void parcelar(Parcelador parcelador) {
        List<Parcela> parcelasGeradas = parcelador.parcelar(this);

        BigDecimal somaParcelas = parcelasGeradas
                .stream()
                .map(Parcela::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(somaParcelas.compareTo(valor) != 0) {
            throw new RuntimeException("A soma das parcelas esta diferente do valor da conta");
        }

        parcelas = Collections.unmodifiableList(parcelasGeradas);
    }
}
