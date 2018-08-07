package br.ufsm.trucocbr.cbr.query;

import br.ufsm.trucocbr.cbr.TrucoDescription;
import jcolibri.cbrcore.CBRQuery;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class QueryEnvidoPe {
    int pontos;


    public QueryEnvidoPe(int pontos) {
        this.pontos = pontos;
    }

    public CBRQuery getQuery() {
        TrucoDescription trucoDescription = new TrucoDescription();
        trucoDescription.setPtsGanhadorEnvido(pontos);
        trucoDescription.setGanhadorPediuEnvido(false);
        trucoDescription.setGanhadorEnvidoMao(false);

        CBRQuery query = new CBRQuery();
        query.setDescription(trucoDescription);

        return query;
    }

}
