package br.ufsm.trucocbr.cbr.query;

import br.ufsm.trucocbr.cbr.TrucoDescription;
import jcolibri.cbrcore.CBRQuery;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class QueryEnvidoMao {

    int pontos;


    public QueryEnvidoMao(int pontos) {
        this.pontos = pontos;
    }

    public CBRQuery getQuery() {
        TrucoDescription trucoDescription = new TrucoDescription();
        trucoDescription.setPtsGanhadorEnvido(pontos);
        trucoDescription.setGanhadorPediuEnvido(true);
        trucoDescription.setGanhadorEnvidoMao(true);
        trucoDescription.setPerdedorNaoEnvido(false);

        CBRQuery query = new CBRQuery();
        query.setDescription(trucoDescription);

        return query;
    }
}
