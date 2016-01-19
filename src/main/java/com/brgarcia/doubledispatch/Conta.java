package com.brgarcia.doubledispatch;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Conta {

    private BigDecimal valor;
    private List<Parcela> parcelas;

    // Deve garantir as constraints ao construir o objeto
    public Conta(BigDecimal valor) {
        Validate.notNull(valor, "Deve informar o valor da conta");
        Validate.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor da conta deve ser maior que zero");

        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    // Não deve permitir que o estado interno seja modificado por outras classes
    public List<Parcela> getParcelas() {
        return Collections.unmodifiableList(parcelas);
    }

    // Toda operação deve garantir que o objeto continue em um estado consistente
    public void parcelar(Parcelador parcelador) {
        // Uso de double dispatch. Garante que o domain service nao altere o estado interno do model
        List<Parcela> parcelasGeradas = parcelador.parcelar(this);

        // Garantindo consistencia
        validar(parcelasGeradas);

        parcelas = parcelasGeradas;
    }

    private void validar(List<Parcela> parcelasGeradas) {
        BigDecimal somaParcelas = parcelasGeradas
                .stream()
                .map(Parcela::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(somaParcelas.compareTo(valor) != 0) {
            throw new RuntimeException("A soma das parcelas esta diferente do valor da conta");
        }
    }
}
