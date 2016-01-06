package com.brgarcia.anemico;


import java.math.BigDecimal;

public class ContaParceladaValidator {

    public void validar(Conta conta) {
        BigDecimal somaParcelas = conta.getParcelas()
                .stream()
                .map(Parcela::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(somaParcelas.compareTo(conta.getValor()) != 0) {
            throw new RuntimeException("A soma das parcelas esta diferente do valor da conta");
        }
    }
}
