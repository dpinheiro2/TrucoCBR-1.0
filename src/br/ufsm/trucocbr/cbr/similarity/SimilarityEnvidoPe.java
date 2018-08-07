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


public class SimilarityEnvidoPe {

    public NNConfig getSimilarityConfig()
    {
        NNConfig config = new NNConfig();
        Attribute attribute;
        LocalSimilarityFunction function;

        //new Equal(); new Interval(param); new Threshold(param);
        //new EnumCyclicDistance(); new EnumDistance();

        /*attribute = new Attribute("", TrucoDescription.class);
        function = ;
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(1.0D));*/

        attribute = new Attribute("ptsGanhadorEnvido", TrucoDescription.class);
        //function = new Equal();
        function = new Interval(new Double(1.2D));
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(5.0D));

        attribute = new Attribute("ganhadorEnvidoMao", TrucoDescription.class);
        function = new Equal();
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(4.0D));

        attribute = new Attribute("ganhadorPediuEnvido", TrucoDescription.class);
        function = new Equal();
        config.addMapping(attribute, function);
        config.setWeight(attribute, new Double(3.0D));



        return config;
    }
}
