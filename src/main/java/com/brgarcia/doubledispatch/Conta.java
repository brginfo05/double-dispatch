package com.brgarcia.doubledispatch;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Conta {

    private static final BigDecimal VALOR_MINIMO = BigDecimal.TEN;

    // Entidades possuem id
    private long numeroConta;
    private BigDecimal valor;
    private List<Parcela> parcelas;

    // Deve garantir as constraints ao construir o objeto
    // Deve existir apenas um construtor primario
    public Conta(long numeroConta, BigDecimal valor) {
        Validate.isTrue(numeroConta > 0, "Numero da conta deve ser maior que zero");
        Validate.notNull(valor, "Deve informar o valor da conta");
        Validate.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor da conta deve ser maior que zero");

        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    // Construtor secundario: deve sempre delegar para o construtor primario
    public Conta(long numeroConta) {
        this(numeroConta, VALOR_MINIMO);
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

    // Declarar o contrato dos recursos que model precisa dentro do próprio model,
    // dessa forma reduzimos totalmente o acoplamento do model.
    // O model não depende de ninguém.
    public interface Parcelador {

        List<Parcela> parcelar(Conta conta);
    }
}
