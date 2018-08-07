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


public class FaltaEnvido extends EstadoEnvido {

    private static LinkedList<Object> estadosAceitos;
    private int pontosRestantesContrario;
    private int pontosRestantes;

    static {
        estadosAceitos = new LinkedList<>();
        estadosAceitos.add(EstadoInicialFlor.class);
        estadosAceitos.add(EstadoInicialEnvido.class);
        estadosAceitos.add(Envido.class);
        estadosAceitos.add(RealEnvido.class);
        estadosAceitos.add(EstadoFinalFlor.class);
        ;
    }

    public FaltaEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor, int pontosRestantesContrario,
                       int pontosRestantes) {
        super(estadoAnterior, estadoFlor, estadosAceitos);
        this.pontosRestantesContrario = pontosRestantesContrario;
        this.pontosRestantes = pontosRestantes;
    }

    public int getPontosAceitos() {
        int pontosAceitos;
        if (this.pontosRestantes > 12) {
            pontosAceitos = this.pontosRestantes - 12;
        } else {
            pontosAceitos = this.pontosRestantesContrario;
        }
        return pontosAceitos;
    }

}
