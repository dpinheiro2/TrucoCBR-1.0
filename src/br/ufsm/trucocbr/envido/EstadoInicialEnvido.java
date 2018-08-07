package br.ufsm.trucocbr.envido;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class EstadoInicialEnvido extends EstadoEnvido {

    public EstadoInicialEnvido() {
        super(null);
        this.pontosEstado = 0;
    }

    @Override
    public int getPontosAceitos() {
        return pontosEstado;
    }

    @Override
    public int getPontosNaoAceitos() {
        return 1;
    }
}
