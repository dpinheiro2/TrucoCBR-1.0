package br.ufsm.trucocbr.mao;

import br.ufsm.trucocbr.Resultado;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoInicialMao extends EstadoMao {

    public EstadoInicialMao() {
        this.estado = Estado.INICIAL;
    }

    @Override
    protected Resultado getResultadoRodada() {
        return null;
    }

    @Override
    protected Resultado getResultadoPrimeiraRodada() {
        return null;
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
