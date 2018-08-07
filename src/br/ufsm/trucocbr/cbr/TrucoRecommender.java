package br.ufsm.trucocbr.cbr;

import br.ufsm.trucocbr.cbr.similarity.SimilarityEnvidoMao;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class TrucoRecommender implements StandardCBRApplication {

    private static TrucoRecommender _instance = null;
    public  static TrucoRecommender getInstance()
    {
        if(_instance == null)
            _instance = new TrucoRecommender();
        return _instance;
    }

    private TrucoRecommender() {    }


    /** Connector object */
    Connector _connector;

    /** CaseBase object */
    CBRCaseBase _caseBase;

    SimilarityEnvidoMao similarityEnvidoMao;

    ArrayList<RetrievalResult> cases;

    @Override
    public void configure() throws ExecutionException {
        try {
            //Emulate data base server
            HSQLDBserver.init();

            // Create a data base connector
            _connector = new DataBaseConnector();
            // Init the ddbb connector with the config file
            _connector.initFromXMLfile(FileIO
                    .findFile("br/ufsm/trucocbr/cbr/databaseconfig.xml"));

            // Create a Lineal case base for in-memory organization
            _caseBase = new LinealCaseBase();


            similarityEnvidoMao = new SimilarityEnvidoMao();
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        // Load cases from connector into the case base
        //will load the cases from the persistence into the memory
        _caseBase.init(_connector);

        // Print the cases
        Collection<CBRCase> cases = _caseBase.getCases();
        for(CBRCase c: cases)
            System.out.println(c);
        return _caseBase;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {

        //:: :: RETRIEVE :: ::

        // Obtain configuration for KNN
        NNConfig simConfig = similarityEnvidoMao.getSimilarityConfig();
        simConfig.setDescriptionSimFunction(new Average());


        // Execute NN
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), cbrQuery, simConfig);

        // Select k cases
        Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, 1);


        System.out.println(":: :: RETRIEVE :: ::");
        cases = new ArrayList<RetrievalResult>();
        for(RetrievalResult rr: eval) {
            if (selectedcases.contains(rr.get_case())) {
                cases.add(rr);
                System.out.println(rr);
                JSONParser parser = new JSONParser();
                try {

                    JSONObject json = (JSONObject) parser.parse(rr.get_case().getDescription().toString());
                    System.out.println("Teste JSon");
                    System.out.println((String) json.get("indJogMao"));
                    System.out.println((String) json.get("jogGanhouEnvido"));
                    System.out.println((String) json.get("jogNaoEnvido"));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
