package com.brgarcia.anemico;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Conta conta = new Conta(BigDecimal.TEN);

        ParceladorNumeroParcelas parceladorNumeroParcelas = new ParceladorNumeroParcelas(3, new ContaParceladaValidator());
        parceladorNumeroParcelas.parcelar(conta);
    }
}
