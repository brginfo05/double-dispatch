package com.brgarcia.doubledispatch;

import java.util.List;

public interface Parcelador {

    List<Parcela> parcelar(Conta conta);
}

