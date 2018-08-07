package br.ufsm.trucocbr.flor;

import java.util.LinkedList;

/**
 * Comando da 3ª Divisão de Exército
 * Adjunto da Seção de Informática
 * 1º Ten Vargas
 * Criado em 22/11/2017.
 */


//TODO implementar Flor e Achico
public class FlorEAchico extends EstadoFlor {

    private static LinkedList<Object> estadosAceitos;
    static {
        estadosAceitos = new LinkedList<Object>();
        estadosAceitos.add(Flor.class);
    }


    public FlorEAchico (EstadoFlor estadoAnterior) {
        super(estadoAnterior, estadosAceitos);
    }


}
