package br.ufsm.trucocbr.truco;

import br.ufsm.trucocbr.envido.EstadoEnvido;
import br.ufsm.trucocbr.envido.EstadoFinalEnvido;
import br.ufsm.trucocbr.envido.EstadoInicialEnvido;
import br.ufsm.trucocbr.flor.EstadoFinalFlor;
import br.ufsm.trucocbr.flor.EstadoFlor;
import br.ufsm.trucocbr.flor.EstadoInicialFlor;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Truco extends EstadoTruco {

    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(EstadoInicialTruco.class);
        estadosAceitos.add(EstadoInicialEnvido.class);
        estadosAceitos.add(EstadoFinalEnvido.class);
        estadosAceitos.add(EstadoInicialFlor.class);
        estadosAceitos.add(EstadoFinalFlor.class);
    }

    public Truco(EstadoTruco estadoAnterior, EstadoEnvido envidoAnterior, EstadoFlor florAnterior) {
        super(estadoAnterior, envidoAnterior, florAnterior, estadosAceitos);
    }

    public int getPontosAceitos() {
        return 2;
    }

    public int getPontosNaoAceitos() {
        return 1;
    }
}
