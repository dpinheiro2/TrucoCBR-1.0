package br.ufsm.trucocbr.truco;

import br.ufsm.trucocbr.envido.EstadoEnvido;
import br.ufsm.trucocbr.flor.EstadoFlor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public abstract class EstadoTruco {

    protected LinkedList<Object> estadosAceitos = new LinkedList<>();

    public EstadoTruco() {
    }

    public EstadoTruco(EstadoTruco estadoAnterior, LinkedList<Object> estadosAceitos) {

    }

    public EstadoTruco(EstadoTruco estadoAnterior, EstadoEnvido estadoEnvido, EstadoFlor estadoFlor,
                       LinkedList<Object> estadosAceptados) {
    }

    public abstract int getPontosAceitos();

    public abstract int getPontosNaoAceitos();
}
