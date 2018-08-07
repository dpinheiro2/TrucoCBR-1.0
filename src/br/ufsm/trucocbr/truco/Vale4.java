package br.ufsm.trucocbr.truco;

import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Vale4 extends EstadoTruco {

    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(Retruco.class);
    }

    public Vale4(EstadoTruco estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
    }

    @Override
    public int getPontosAceitos() {
        return 4;
    }

    @Override
    public int getPontosNaoAceitos() {
        return 3;
    }
}
