package br.ufsm.trucocbr.cbr.query;

import br.ufsm.trucocbr.cbr.TrucoDescription;
import jcolibri.cbrcore.CBRQuery;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class QueryTrucoMao1 {

    int hierarquiaCartaAlta;
    int hierarquiaCartaMedia;
    int hierarquiaCartaBaixa;

    public QueryTrucoMao1(int hierarquiaCartaAlta, int hierarquiaCartaMedia, int hierarquiaCartaBaixa) {
        this.hierarquiaCartaAlta = hierarquiaCartaAlta;
        this.hierarquiaCartaMedia = hierarquiaCartaMedia;
        this.hierarquiaCartaBaixa = hierarquiaCartaBaixa;
    }

    public CBRQuery getQuery() {
        TrucoDescription trucoDescription = new TrucoDescription();
        trucoDescription.setHierarquiaAVencMao(hierarquiaCartaAlta);
        trucoDescription.setHierarquiaMVencMao(hierarquiaCartaMedia);
        trucoDescription.setHierarquiaBVencMao(hierarquiaCartaBaixa);
        trucoDescription.setVencedorMaoIsMao(true);
        trucoDescription.setVencedorRod1Mao(true);

        CBRQuery query = new CBRQuery();
        query.setDescription(trucoDescription);

        return query;
    }
}
