package br.ufsm.trucocbr.flor;

import br.ufsm.trucocbr.envido.EstadoEnvido;
import br.ufsm.trucocbr.envido.EstadoFinalEnvido;
import br.ufsm.trucocbr.envido.EstadoInicialEnvido;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Flor extends EstadoFlor {


    private static LinkedList<Object> estadosAceitos;
    static {
        estadosAceitos = new LinkedList<Object>();
        estadosAceitos.add(EstadoInicialFlor.class);
    }

    public Flor(EstadoFlor estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.pontosEstado = 3;
    }

}
