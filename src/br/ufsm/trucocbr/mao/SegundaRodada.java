package br.ufsm.trucocbr.mao;

import br.ufsm.trucocbr.Resultado;
import br.ufsm.trucocbr.TrucoData;

import java.util.LinkedList;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class SegundaRodada extends EstadoMao {

    private Resultado resultadoSegunda;
    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(PrimeiraRodada.class);
    }

    public SegundaRodada(EstadoMao estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.estado = Estado.SEGUNDA;
    }

    public SegundaRodada(EstadoMao estadoAnterior, Resultado resultadoSegunda) {
        super(estadoAnterior, estadosAceitos);
        this.resultadoSegunda = resultadoSegunda;
        this.estado = Estado.SEGUNDA;
    }

    @Override
    protected Resultado getResultadoRodada() {
        return resultadoSegunda;
    }

    @Override
    protected Resultado getResultadoPrimeiraRodada() {
        return estadoAnterior.getResultadoPrimeiraRodada();
    }

    @Override
    public Boolean isMaoConcluida() {
        boolean isRodadaConcluida = false;

        if ((this.estadoAnterior.getResultadoRodada() == Resultado.EMPATE) && (resultadoSegunda != Resultado.EMPATE))
            isRodadaConcluida = true;

        if ((this.estadoAnterior.getResultadoRodada() != Resultado.EMPATE) && (resultadoSegunda == Resultado.EMPATE))
            isRodadaConcluida = true;

        if ((this.estadoAnterior.getResultadoRodada() == resultadoSegunda) && (resultadoSegunda == Resultado.EMPATE))
            isRodadaConcluida = true;

        return isRodadaConcluida;
    }

    @Override
    protected Resultado getVencedorParcial() {
        Resultado resultadoParcial = null;
        if ((estadoAnterior.getResultadoRodada() == resultadoSegunda)
                && (resultadoSegunda != Resultado.EMPATE)) {
            resultadoParcial = resultadoSegunda;
        } else if ((estadoAnterior.getResultadoRodada() != Resultado.EMPATE)
                && (resultadoSegunda == Resultado.EMPATE)) {
            resultadoParcial = estadoAnterior.getResultadoRodada();
        } else if ((estadoAnterior.getResultadoRodada() == Resultado.EMPATE)
                && (resultadoSegunda != Resultado.EMPATE)) {
            resultadoParcial = resultadoSegunda;
        } else {
            resultadoParcial = Resultado.EMPATE;
        }
        return resultadoParcial;
    }

    @Override
    public int getVencedorMao() {
        int ganhador = -1;
        switch (this.getVencedorParcial()) {
            case HUMANO:
                ganhador = TrucoData.HUMANO;
                break;

            case COMPUTADOR:
                ganhador = TrucoData.COMPUTADOR;
                break;

            case EMPATE:
                break;
        }

        return ganhador;

    }
}
