package br.ufsm.trucocbr.flor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoFinalFlor extends EstadoFlor {
    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<Object>();
        estadosAceitos.add(EstadoInicialFlor.class);
        estadosAceitos.add(Flor.class);
        estadosAceitos.add(ContraFlor.class);
        estadosAceitos.add(ContraFlorResto.class);
    }

    public EstadoFinalFlor(EstadoFlor estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.pontosEstado = 0;
    }

}
