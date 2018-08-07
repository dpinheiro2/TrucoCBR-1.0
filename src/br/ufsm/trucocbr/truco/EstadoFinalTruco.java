package br.ufsm.trucocbr.truco;

import br.ufsm.trucocbr.envido.EstadoEnvido;

/**
 * Comando da 3ª Divisão de Exército
 * Adjunto da Seção de Informática
 * 1º Ten Vargas
 * Criado em 22/11/2017.
 */


public class EstadoFinalTruco extends EstadoTruco {

    public EstadoFinalTruco(EstadoTruco estadoAnterior) {
        super();

    }


    @Override
    public int getPontosAceitos() {
        return 0;
    }

    @Override
    public int getPontosNaoAceitos() {
        return 0;
    }
}
