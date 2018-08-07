package br.ufsm.trucocbr.envido;

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


public class Envido extends EstadoEnvido {

    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(EstadoInicialFlor.class);
        estadosAceitos.add(EstadoInicialEnvido.class);
        estadosAceitos.add(Envido.class);
        estadosAceitos.add(EstadoFinalFlor.class);
    }

    public Envido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor) {
        super(estadoAnterior, estadoFlor, estadosAceitos);
        this.pontosEstado = 2;
    }
}
