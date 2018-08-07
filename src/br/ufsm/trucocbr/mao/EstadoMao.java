package br.ufsm.trucocbr.mao;

import br.ufsm.trucocbr.Resultado;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public abstract class EstadoMao {

    protected EstadoMao estadoAnterior;
    protected Estado estado;

    public EstadoMao(EstadoMao estadoAnterior, LinkedList<Object> estadosAceitos) {
        if (estadosAceitos.contains(estadoAnterior.getClass())) {
            this.estadoAnterior = estadoAnterior;
        }
    }

    public EstadoMao() {
        this.estadoAnterior = null;
    }

    public Estado getEstado() {
        return estado;
    }

    protected abstract Resultado getResultadoRodada();

    protected abstract Resultado getResultadoPrimeiraRodada();

    public abstract Boolean isMaoConcluida();

    protected abstract Resultado getVencedorParcial();

    public abstract int getVencedorMao();
}
