package br.ufsm.trucocbr.truco;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Retruco extends EstadoTruco {


    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(Truco.class);
    }

    public Retruco(EstadoTruco estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
    }

    @Override
    public int getPontosAceitos() {
        return 3;
    }

    @Override
    public int getPontosNaoAceitos() {
        return 2;
    }
}
