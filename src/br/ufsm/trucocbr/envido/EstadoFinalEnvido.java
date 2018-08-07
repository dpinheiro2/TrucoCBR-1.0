package br.ufsm.trucocbr.envido;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoFinalEnvido extends EstadoEnvido {

    public EstadoFinalEnvido(EstadoEnvido estadoAnteriorEnvido) {
        super(estadoAnteriorEnvido);
        this.pontosEstado = 0;
    }

}
