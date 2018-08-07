package br.ufsm.trucocbr.flor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class ContraFlorResto extends EstadoFlor {

    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<Object>();
        estadosAceitos.add(Flor.class);
        estadosAceitos.add(ContraFlor.class);
    }

    public ContraFlorResto(EstadoFlor estadoAnterior, int pontosASomar) {
        super(estadoAnterior, estadosAceitos);
        this.pontosEstado = pontosASomar;
    }

    public int getPontosAceitos() {
        return pontosEstado;
    }

    public int getPontosNaoAceitos() {
        return estadoAnterior.getPontosAceitos();
    }

}
