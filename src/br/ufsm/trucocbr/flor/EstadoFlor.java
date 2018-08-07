package br.ufsm.trucocbr.flor;

import br.ufsm.trucocbr.envido.EstadoEnvido;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public abstract class EstadoFlor {

    protected EstadoFlor estadoAnterior;
    protected int pontosEstado;
    private static LinkedList<Object> estadoFinal;

    static {
        estadoFinal = new LinkedList<Object>();
        estadoFinal.add(EstadoFinalFlor.class);
    }

    public EstadoFlor(EstadoFlor estadoAnterior, LinkedList<Object> estadosAceitos) {
        if (!estadoFinal.contains(estadoAnterior.getClass()) && estadosAceitos.contains(estadoAnterior.getClass()))
            this.estadoAnterior = estadoAnterior;
    }

    public EstadoFlor() {
        this.estadoAnterior = null;
    }

    public int getPontosAceitos() {
        return (this.pontosEstado + estadoAnterior.getPontosAceitos());
    }

    public int getPontosNaoAceitos() {
        return 0;
    }

    public int getPontosEstado() {
        return pontosEstado;
    }

}
