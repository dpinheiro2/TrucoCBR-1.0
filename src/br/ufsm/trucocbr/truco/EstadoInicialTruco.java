package br.ufsm.trucocbr.truco;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoInicialTruco extends EstadoTruco {

    public EstadoInicialTruco() {
        super();
    }

    @Override
    public int getPontosAceitos() {
        return 1;
    }

    @Override
    public int getPontosNaoAceitos() {
        return 1;
    }
}
