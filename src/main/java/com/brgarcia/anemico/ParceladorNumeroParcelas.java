package com.brgarcia.anemico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ParceladorNumeroParcelas {

    private final ContaParceladaValidator contaParceladaValidator;
    private int numeroParcelas;

    public ParceladorNumeroParcelas(int numeroParcelas,
                                    ContaParceladaValidator contaParceladaValidator) {

        this.numeroParcelas = numeroParcelas;
        this.contaParceladaValidator = contaParceladaValidator;
    }

    public List<Parcela> parcelar(Conta conta) {
        List<Parcela> parcelas = new ArrayList();

        for(int i = 0; i < numeroParcelas; i++) {
            parcelas.add(calcularParcela(conta));
        }

        conta.setParcelas(parcelas);

        contaParceladaValidator.validar(conta);

        return parcelas;
    }

    private Parcela calcularParcela(Conta conta) {
        BigDecimal valorParcela = conta.getValor()
                .divide(BigDecimal.valueOf(numeroParcelas), RoundingMode.HALF_UP);

        return new Parcela(valorParcela);
    }
}
