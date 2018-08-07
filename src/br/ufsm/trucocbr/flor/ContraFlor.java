package br.ufsm.trucocbr.flor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class ContraFlor extends EstadoFlor {

    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<Object>();
        estadosAceitos.add(Flor.class);
    }

    public ContraFlor(EstadoFlor estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.pontosEstado = 3;
    }

    public int getPontosNaoAceitos() {
        return estadoAnterior.getPontosAceitos() + 1;
    }

}
