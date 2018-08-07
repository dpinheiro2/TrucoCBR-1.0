package br.ufsm.trucocbr.flor;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoInicialFlor extends EstadoFlor {

    public EstadoInicialFlor() {
        super();
        this.pontosEstado = 0;
    }

    @Override
    public int getPontosAceitos() {
        return pontosEstado;
    }
}
