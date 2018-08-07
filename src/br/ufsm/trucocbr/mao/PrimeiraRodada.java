package br.ufsm.trucocbr.mao;

import br.ufsm.trucocbr.Resultado;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */

public class PrimeiraRodada extends EstadoMao {

    private Resultado resultadoPrimeira;
    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(EstadoInicialMao.class);
    }

    public PrimeiraRodada(EstadoMao estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.estado = Estado.PRIMEIRA;
    }

    public PrimeiraRodada(EstadoMao estadoAnterior, Resultado resultadoPrimeira) {
        super(estadoAnterior, estadosAceitos);
        this.resultadoPrimeira = resultadoPrimeira;
        this.estado = Estado.PRIMEIRA;
    }

    @Override
    protected Resultado getResultadoRodada() {
        return resultadoPrimeira;
    }

    @Override
    protected Resultado getResultadoPrimeiraRodada() {
        return resultadoPrimeira;
    }

    @Override
    public Boolean isMaoConcluida() {
        return false;
    }

    @Override
    protected Resultado getVencedorParcial() {
        return null;
    }

    @Override
    public int getVencedorMao() {
        return -1;
    }
}
