package com.brgarcia.doubledispatch;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Conta conta = new Conta(BigDecimal.TEN);
        conta.parcelar(new ParceladorNumeroParcelas(2));
    }
}
