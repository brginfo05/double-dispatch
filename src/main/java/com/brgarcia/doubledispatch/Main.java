package com.brgarcia.doubledispatch;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        new Conta(BigDecimal.TEN)
                .parcelar(new ParceladorNumeroParcelas(3));
    }
}
