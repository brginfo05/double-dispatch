package com.brgarcia.anemico;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Conta conta = new Conta(BigDecimal.TEN);

        Parcelador parcelador = new ParceladorNumeroParcelas(3, new ContaParceladaValidator());
        parcelador.parcelar(conta);
    }
}
