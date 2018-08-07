package br.ufsm.trucocbr.mao;

import br.ufsm.trucocbr.Resultado;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Mao {

    private EstadoMao estadoMao;

    public Mao() {
        this.estadoMao = new EstadoInicialMao();
    }

    public int getRodadasJogadas() {
        int tamanho = 0;
        for(Estado estado : Estado.values()) {
            if (estadoMao.getEstado().equals(estado))
                break;
            tamanho++;
        }
        return tamanho;
    }


    public void fluxo(Resultado resultado) {
        if (this.getRodadasJogadas() < 3) {
            switch (this.getRodadasJogadas()) {
                case 0:
                    this.estadoMao = new PrimeiraRodada(this.estadoMao, resultado);
                    break;

                case 1:
                    this.estadoMao = new SegundaRodada(this.estadoMao, resultado);
                    break;

                case 2:
                    this.estadoMao = new TerceiraRodada(this.estadoMao, resultado);
                    break;

                case 3:
                    break;
            }
        }
    }

    public Boolean isMaoConcluida() {
        return this.estadoMao.isMaoConcluida();
    }

    public int getVencedorMao() {

        return this.estadoMao.getVencedorMao();
    }

    public boolean isPrimeiraRodada() {
        return this.getRodadasJogadas() == 1;
    }



}
