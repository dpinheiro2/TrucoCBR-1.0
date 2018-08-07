package br.ufsm.trucocbr.envido;

import br.ufsm.trucocbr.flor.EstadoFlor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public abstract class EstadoEnvido {

    protected EstadoEnvido estadoAnterior;
    protected int pontosEstado;
    private static LinkedList<Object> estadoFinal;

    static {
        estadoFinal = new LinkedList<Object>();
        estadoFinal.add(EstadoFinalEnvido.class);
    }

    public EstadoEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor, LinkedList<Object> estadosAceitos) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoEnvido(EstadoEnvido estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public int getPontosAceitos() {
        return (this.pontosEstado + estadoAnterior.getPontosAceitos());
    }

    public int getPontosNaoAceitos() {
        if (estadoAnterior instanceof EstadoInicialEnvido) {
            return (estadoAnterior.getPontosNaoAceitos());
        } else {
            return (estadoAnterior.getPontosAceitos());
        }
    }

    public int getPontosEstado() {
        return this.pontosEstado;
    }
}
