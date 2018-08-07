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


public class TerceiraRodada extends EstadoMao {

    private Resultado resultadoTerceira;
    private static LinkedList<Object> estadosAceitos;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(SegundaRodada.class);
    }

    public TerceiraRodada(EstadoMao estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
        this.estado = Estado.TERCEIRA;
    }

    public TerceiraRodada(EstadoMao estadoAnterior, Resultado resultadoTerceira) {
        super(estadoAnterior, estadosAceitos);
        this.resultadoTerceira = resultadoTerceira;
        this.estado = Estado.TERCEIRA;
    }

    @Override
    protected Resultado getResultadoRodada() {
        return resultadoTerceira;
    }

    @Override
    protected Resultado getResultadoPrimeiraRodada() {
        return estadoAnterior.getResultadoPrimeiraRodada();
    }

    @Override
    public Boolean isMaoConcluida() {
        return true;
    }

    @Override
    protected Resultado getVencedorParcial() {
        Resultado resultado = null;
        if (resultadoTerceira != Resultado.EMPATE) {
            resultado = resultadoTerceira;
        } else {
            resultado = this.getResultadoPrimeiraRodada();
        }
        return resultado;
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
