package br.ufsm.trucocbr.cbr.similarity;

import br.ufsm.trucocbr.cbr.TrucoDescription;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class SimilarityTrucoMao1 {

    public NNConfig getSimilarityConfig()
    {
        NNConfig config = new NNConfig();
        Attribute attribute;
        LocalSimilarityFunction function;

        attribute = new Attribute("hierarquiaAVencMao", TrucoDescription.class);
        function = new Interval(new Double(1.5D));
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(4.0D));

        attribute = new Attribute("hierarquiaMVencMao", TrucoDescription.class);
        function = new Interval(new Double(1.5D));
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(4.0D));

        attribute = new Attribute("hierarquiaBVencMao", TrucoDescription.class);
        function = new Interval(new Double(1.5D));
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(4.0D));

        attribute = new Attribute("vencedorMaoIsMao", TrucoDescription.class);
        function = new Equal();
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(5.0D));

        attribute = new Attribute("vencedorRod1Mao", TrucoDescription.class);
        function = new Equal();
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(2.0D));


        return config;
    }
}
