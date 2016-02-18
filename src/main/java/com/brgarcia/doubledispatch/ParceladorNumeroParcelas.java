package com.brgarcia.doubledispatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ParceladorNumeroParcelas implements Conta.Parcelador {

    private int numeroParcelas;

    public ParceladorNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public List<Parcela> parcelar(Conta conta) {
        return IntStream
                .rangeClosed(1, numeroParcelas)
                .mapToObj(numeroParcela -> calcularParcela(conta, numeroParcela))
                .collect(toList());
    }

    private Parcela calcularParcela(Conta conta, int numeroParcela) {
        BigDecimal valorParcela = conta.getValor()
                .divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);

        if(numeroParcela == numeroParcelas) {
            BigDecimal valorSomadoParcelas = valorParcela.multiply(BigDecimal.valueOf(numeroParcelas));
            BigDecimal diferenca = conta.getValor().subtract(valorSomadoParcelas);

            valorParcela = valorParcela.add(diferenca);
        }

        return new Parcela(valorParcela);
    }
}
