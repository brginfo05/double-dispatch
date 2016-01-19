package com.brgarcia.anemico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ParceladorNumeroParcelas implements Parcelador {

    private final ContaParceladaValidator contaParceladaValidator;
    private int numeroParcelas;

    public ParceladorNumeroParcelas(int numeroParcelas,
                                    ContaParceladaValidator contaParceladaValidator) {

        this.numeroParcelas = numeroParcelas;
        this.contaParceladaValidator = contaParceladaValidator;
    }

    @Override
    public void parcelar(Conta conta) {
        List<Parcela> parcelas = IntStream
                .rangeClosed(1, numeroParcelas)
                .mapToObj(numeroParcela -> calcularParcela(conta, numeroParcela))
                .collect(toList());

        conta.setParcelas(parcelas);

        contaParceladaValidator.validar(conta);
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
