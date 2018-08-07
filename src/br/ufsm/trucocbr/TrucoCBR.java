package br.ufsm.trucocbr;


import br.ufsm.trucocbr.cbr.query.QueryEnvidoPe;
import br.ufsm.trucocbr.cbr.query.QueryTrucoPe;
import br.ufsm.trucocbr.cbr.similarity.SimilarityEnvidoPe;
import br.ufsm.trucocbr.cbr.similarity.SimilarityTrucoPe;
import br.ufsm.trucocbr.envido.*;
import br.ufsm.trucocbr.flor.*;
import br.ufsm.trucocbr.mao.Mao;
import br.ufsm.trucocbr.cbr.HSQLDBserver;
import br.ufsm.trucocbr.cbr.query.QueryEnvidoMao;
import br.ufsm.trucocbr.cbr.query.QueryTrucoMao1;
import br.ufsm.trucocbr.cbr.similarity.SimilarityEnvidoMao;
import br.ufsm.trucocbr.cbr.similarity.SimilarityTrucoMao1;
import br.ufsm.trucocbr.truco.*;
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
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;


/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class TrucoCBR extends JFrame implements ActionListener, StandardCBRApplication {

    //final static Logger logger = LogManager.getLogger(TrucoCBR.class);
    final static Logger logger = Logger.getLogger(TrucoCBR.class);

    private static TrucoCBR _instance = null;
    public  static TrucoCBR getInstance()
    {
        if(_instance == null)
            _instance = new TrucoCBR();
        return _instance;
    }

    JPanel pnlStatus, pnlActions, pnlPts, pnlEnvido, pnlFlor, pnlTruco, pnlAceite, pnlFuga, pnlCartas, pnlCartasComp,
            pnlCartasHumano, pnlCartasMao;
    RoundPanel pnlRodada1, pnlRodada2, pnlRodada3;
    JLabel lblInfo, lblClock, lblPtsComp, lblPtsHumano;
    JButton btnEnvido, btnRealEnvido, btnFaltaEnvido, btnFlor, btnContraFlor, btnContraFlorResto,
            btnTruco, btnRetruco, btnVale4, btnQuero, btnNaoQuero, btnIrBaralho;
    JMenuBar jMenuBar;
    JMenu jMenuJogo, jMenuAjuda;
    JMenuItem jmiNovoJogo, jmiSair;
    OptionsPanel pnlOptions;

    Baralho baralho;
    Baralho cartasHumano;
    Baralho cartasComputador;

    Player playerHumano;
    Player playerComputador;

    private Timer timer;
    private int currentSegundo = 0;
    private int currentMinuto = 0;
    private int currentHora = 0;
    private int velocidade = 1000;

    boolean isHumanoMao = true;
    int ptsComp = 0;
    int ptsHumano = 0;

    int numRodada = 1;
    int numMao = 1;

    int playerAtual;


    int playerCantouEnvido = -1;
    int playerCantouFlor = -1;
    int playerCantouTruco = -1;
    int playerFoiBaralho = -1;
    boolean isFlorCantada = false;
    boolean isEnvidoPedido = false;
    boolean isTrucoPedido = false;
    boolean isMaoConcluida = false;
    EstadoFlor estadoAtualFlor;
    EstadoEnvido estadoAtualEnvido;
    EstadoTruco estadoAtualTruco;
    List<JButton> buttonsFlor, buttonsEnvido, buttonsTruco, buttonsAceite;
    LinkedList<Carta> cartasNaMesa;
    Mao mao;

    Resultado vencedorRodada1;
    Resultado vencedorRodada2;
    Resultado vencedorRodada3;

    boolean isEndPartida = false;
    boolean isEndMao = false;
    boolean isEndRodada = false;

    int estadoPartida;
    int estadoMao;
    int estadoRodada;

    ClassLoader loader;

    /** Variáveis CBR */
    Connector _connector;
    CBRCaseBase _caseBase;

    QueryTrucoMao1 queryTrucoMao1;
    QueryEnvidoMao queryEnvidoMao;
    QueryEnvidoPe queryEnvidoPe;
    QueryTrucoPe queryTrucoPe;

    CBRQuery query;

    SimilarityEnvidoMao similarityEnvidoMao;
    SimilarityTrucoMao1 similarityTrucoMao1;
    SimilarityEnvidoPe similarityEnvidoPe;
    SimilarityTrucoPe similarityTrucoPe;

    ArrayList<RetrievalResult> cases;

    JSONObject jsonCaseRetrieved;


    public TrucoCBR() {

        super("TrucoCBR "+ TrucoData.VERSION);
        loader = getClass().getClassLoader();
        ArrayList<Image> icons = new ArrayList<Image>();
        icons.add(Toolkit.getDefaultToolkit().getImage(loader.getResource("br/ufsm/trucocbr/res/img/logo56.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(loader.getResource("br/ufsm/trucocbr/res/img/logo16.png")));
        this.setIconImages(icons);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(loader.getResource("br/ufsm/trucocbr/res/img/background.jpg"))));
        setLayout(new BorderLayout());

        jMenuBar = new JMenuBar();

        jMenuJogo = new JMenu("Jogo");
        jmiNovoJogo = new JMenuItem("Novo Jogo");
        jmiNovoJogo.addActionListener(this);
        jMenuJogo.add(jmiNovoJogo);
        jMenuJogo.addSeparator();
        jmiSair = new JMenuItem("Sair");
        jmiSair.addActionListener(this);
        jMenuJogo.add(jmiSair);

        jMenuBar.add(jMenuJogo);

        //TODO btnSobre + btnRegras
        jMenuAjuda = new JMenu("Ajuda");
        jMenuBar.add(jMenuAjuda);

        add(jMenuBar, BorderLayout.PAGE_START);

        add(createActionPanel(), BorderLayout.LINE_END);

        pnlStatus = new JPanel(new BorderLayout());
        add(pnlStatus, BorderLayout.PAGE_END);


        lblClock = new JLabel("Tempo: 00:00:00");
        lblInfo = new JLabel("Jogo não iniciado");

        pnlStatus.add(lblInfo, BorderLayout.LINE_START);
        pnlStatus.add(lblClock, BorderLayout.LINE_END);
        pnlStatus.setBorder(new EmptyBorder(2,5,2,5));


        buttonsFlor = new ArrayList<>(Arrays.asList(btnFlor, btnContraFlor, btnContraFlorResto));
        buttonsEnvido = new ArrayList<>(Arrays.asList(btnEnvido, btnRealEnvido, btnFaltaEnvido));
        buttonsTruco = new ArrayList<>(Arrays.asList(btnTruco, btnRetruco, btnVale4));
        buttonsAceite = new ArrayList<>(Arrays.asList(btnQuero, btnNaoQuero));

        enableDisableAllButtons(false);
        cartasNaMesa = new LinkedList<Carta>();
        mao = new Mao();
        estadoAtualFlor = new EstadoInicialFlor();
        estadoAtualEnvido = new EstadoInicialEnvido();
        estadoAtualTruco = new EstadoInicialTruco();

        pack();
        Dimension size = getSize();
        size.height = 600;
        size.width = 600;
        setSize(size);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                sair();
            }
        });

        try {
            configure();
            preCycle();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
            e.printStackTrace();
        }
    }

    public void novoJogo(){

        isHumanoMao = pnlOptions.humano_button.isSelected();
        createBaralho();
        baralho.embaralhar2();
        darAsCartas();
        createPlayers();
        playerComputador.setOrderCartas();
        playerHumano.setOrderCartas();
        iniciarContagem();
        playerAtual = isHumanoMao ? TrucoData.HUMANO : TrucoData.COMPUTADOR;
        setStatus(playerAtual);
        playerHumano.resetPontos();
        playerComputador.resetPontos();
        atualizaPontuacao();

        if (pnlCartas != null) {
            pnlCartas.removeAll();
            remove(pnlCartas);
        }
        add(createCartasPanel(), BorderLayout.CENTER);
        pnlCartas.repaint();
        invalidate();
        validate();
        pnlCartas.invalidate();
        pnlCartas.validate();

        estadoPartida = TrucoData.NOVA_MAO;
        estadoMao = TrucoData.NOVA_RODADA;
        estadoRodada = isHumanoMao ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;

        queryEnvidoMao = new QueryEnvidoMao(playerComputador.getEnvido());
        queryTrucoMao1 = new QueryTrucoMao1(playerComputador.getHierarquiaCartaAlta(), playerComputador.getHierarquiaCartaMedia(),
                playerComputador.getHierarquiaCartaBaixa());
        queryEnvidoPe = new QueryEnvidoPe(playerComputador.getEnvido());
        queryTrucoPe = new QueryTrucoPe(playerComputador.getHierarquiaCartaAlta(), playerComputador.getHierarquiaCartaMedia(),
                playerComputador.getHierarquiaCartaBaixa());

        mao = new Mao();

        vencedorRodada1 = Resultado.EMPATE;
        vencedorRodada2 = Resultado.EMPATE;
        vencedorRodada3 = Resultado.EMPATE;

        if (playerAtual == TrucoData.HUMANO && mao.getRodadasJogadas()==0)
            stateButtonsInicial();

        System.out.println(playerComputador.getHierarquiaCartaAlta());
        System.out.println(playerComputador.getHierarquiaCartaMedia());
        System.out.println(playerComputador.getHierarquiaCartaBaixa());
        System.out.println(getSeqMaoRodada0());

        play();
    }

    public void play() {

        SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {

                logger.info("Thread do game loop");
                while(!isPartidaConcluida()){
                    switch(estadoPartida){
                        case TrucoData.NOVA_MAO:
                            logger.info("Começa Mão");
                            while (!isMaoConcluida) {
                                switch (estadoMao) {
                                    case TrucoData.NOVA_RODADA:
                                        //logger.info("Começa Rodada");
                                        isEndRodada = false;
                                        while (!isEndRodada) {
                                            switch (estadoRodada) {
                                                case TrucoData.HUMANO_VEZ:
                                                    break;
                                                case TrucoData.COMPUTADOR_VEZ:
                                                    //logger.info("Vez do computador");
                                                    //Thread sleep para para fazer de conta que o Computador está pensando na jogada
                                                    try{
                                                        //Thread.sleep(2000);
                                                    }catch(Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    acoesComputador();
                                                    break;
                                                case TrucoData.FIM_RODADA:
                                                    //logger.info("Fim da Rodada");
                                                    estadoMao = TrucoData.NOVA_RODADA;
                                                    isEndRodada = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case TrucoData.FIM_MAO:
                                        logger.info("Fim da Mão");
                                        initValuesNewMao();
                                        break;
                                }
                            }
                            break;
                        case TrucoData.FIM_PARTIDA:
                            //logger.info("Fim da Partida");
                            setPartidaFinalizada();
                            //return;
                    }
                    try{
                       // Thread.sleep(3);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };

        worker.execute();

        logger.info("Entrou no método play");
        Thread t = new Thread(){
            public void run(){
                /*logger.info("Thread do game loop");
                while(!isPartidaConcluida()){
                    switch(estadoPartida){
                        case TrucoData.NOVA_MAO:
                            while (!isMaoConcluida) {
                                switch (estadoMao) {
                                    case TrucoData.NOVA_RODADA:
                                        isEndRodada = false;
                                        while (!isEndRodada) {
                                            switch (estadoRodada) {
                                                case TrucoData.HUMANO_VEZ:
                                                    break;
                                                case TrucoData.COMPUTADOR_VEZ:
                                                    //Thread sleep para para fazer de conta que o Computador está pensando na jogada
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch(Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    acoesComputador();
                                                    break;
                                                case TrucoData.FIM_RODADA:
                                                    estadoMao = TrucoData.NOVA_RODADA;
                                                    isEndRodada = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case TrucoData.FIM_MAO:
                                        initValuesNewMao();
                                        break;
                                }
                            }
                            break;
                        case TrucoData.FIM_PARTIDA:
                            setPartidaFinalizada();
                            return;
                    }
                    try{
                        Thread.sleep(3);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }*/
            }
        };
        t.start();
    }

    public void acoesComputador() {
        logger.info("vez do computador");
        if (mao.getRodadasJogadas() == 0) {
            if (isFlorCantada) {
                if (playerComputador.getFlor() > 0) {
                    if (playerCantouEnvido == TrucoData.HUMANO) {
                        if (estadoAtualFlor.getClass() == Flor.class) {
                            if (playerComputador.getFlor() > 35 && getPtsRestantes(TrucoData.COMPUTADOR) < 6) {
                                JOptionPane.showMessageDialog(this, "Contra-Flor e o Resto!", "Computador Disse:", 1, null);
                                contraFlorAlResto();
                            } else if (playerComputador.getFlor() > 35) {
                                JOptionPane.showMessageDialog(this, "Contra-Flor!", "Computador Disse:", 1, null);
                                contraFlorAlResto();
                            } else {
                                JOptionPane.showMessageDialog(this, "Flor e me achico!", "Computador Disse:", 1, null);
                                queroFlor();
                            }
                        }
                    } else if (estadoAtualFlor.getClass() == ContraFlor.class) {
                        if (playerComputador.getFlor() > 35 && getPtsRestantes(TrucoData.COMPUTADOR) < 6) {
                            JOptionPane.showMessageDialog(this, "Contra-Flor e o Resto!", "Computador Disse:", 1, null);
                            contraFlorAlResto();
                        } else if (playerComputador.getFlor() > 35) {
                            JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                            queroFlor();
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                            naoQueroFlor();
                        }

                    } else if (estadoAtualFlor.getClass() == ContraFlorResto.class) {
                        if (playerComputador.getFlor() > 35 && getPtsRestantes(TrucoData.COMPUTADOR) < 6) {
                            JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                            queroFlor();
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                            naoQueroFlor();
                        }
                    }
                }

            }else if (isEnvidoPedido) {
                if (playerComputador.getFlor() > 0) {
                    JOptionPane.showMessageDialog(this, "Contra Flor é Proibido!", "Computador Disse:", 1, null);
                    flor();
                } else
                //TODO Implementar Blefe
                if (playerComputador.getCartas().getEnvido() > 0) {

                    if (playerCantouEnvido == TrucoData.HUMANO) {

                        /* Consulta CBR para saber se pede ou não Envido
                        * Para Real Envido ou Falta Envido não existem casos suficientes na base de casos */
                        query = queryEnvidoPe.getQuery();
                        TrucoCBR.getInstance().cycle(query, TrucoData.QUERY_ENVIDO_PE);

                        if (estadoAtualEnvido.getClass() == Envido.class) {
                            //TODO implementar envido! envido!
                            if (jsonCaseRetrieved != null && playerComputador.getCartas().getEnvido() > 24 &&
                                    playerComputador.getCartas().getEnvido() < 31) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else if (playerComputador.getCartas().getEnvido() > 24 && playerComputador.getCartas().getEnvido() < 31) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else if (playerComputador.getCartas().getEnvido() > 30 && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                                JOptionPane.showMessageDialog(this, "Falta Envido!", "Computador Disse:", 1, null);
                                faltaEnvido();
                            } else if (playerComputador.getCartas().getEnvido() > 30) {
                                JOptionPane.showMessageDialog(this, "Real Envido!", "Computador Disse:", 1, null);
                                realEnvido();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroEnvido();
                            }
                        } else if (estadoAtualEnvido.getClass() == RealEnvido.class) {
                            if (playerComputador.getCartas().getEnvido() > 30  && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                                JOptionPane.showMessageDialog(this, "Falta Envido!", "Computador Disse:", 1, null);
                                faltaEnvido();
                            } else if (playerComputador.getCartas().getEnvido() > 30) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroEnvido();
                            }
                        } else if (estadoAtualEnvido.getClass() == FaltaEnvido.class) {
                            if (playerComputador.getCartas().getEnvido() > 30 && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroEnvido();
                            }
                        }

                    } else {
                        if (estadoAtualEnvido.getClass() == RealEnvido.class) {
                            if (playerComputador.getCartas().getEnvido() > 30 && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                                JOptionPane.showMessageDialog(this, "Falta Envido!", "Computador Disse:", 1, null);
                                faltaEnvido();
                            } else if (playerComputador.getCartas().getEnvido() > 30) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroEnvido();
                            }
                        } else if (estadoAtualEnvido.getClass() == FaltaEnvido.class) {
                            if (playerComputador.getCartas().getEnvido() > 30 && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroEnvido();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroEnvido();
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                    naoQueroEnvido();
                }

            } else if (!isEnvidoPedido && estadoAtualTruco.getClass() == EstadoInicialTruco.class &&
                    !(estadoAtualEnvido.getClass() == EstadoFinalEnvido.class && estadoAtualFlor.getClass() == EstadoInicialFlor.class)) {

                if (playerComputador.getFlor() > 0) {
                    JOptionPane.showMessageDialog(this, "Lá nos mato do Darci\n" +
                            "Num rolete de cipó\n" +
                            "Tem um macaco de colete\n" +
                            "Com uma FLOR no paletó", "Computador Disse:", 1, null);
                    flor();
                } else if (playerComputador.getCartas().getEnvido() > 0) {
                    //TODO implementar Blefe
                    if (estadoAtualEnvido.getClass() == EstadoInicialEnvido.class) {

                    /* Consulta CBR para saber se pede ou não Envido
                    * Para Real Envido ou Falta Envido não existem casos suficientes na base de casos */
                        query = queryEnvidoMao.getQuery();
                        TrucoCBR.getInstance().cycle(query, TrucoData.QUERY_ENVIDO_MAO);

                        if (jsonCaseRetrieved != null && playerComputador.getCartas().getEnvido() > 24 &&
                                playerComputador.getCartas().getEnvido() < 31) {
                            JOptionPane.showMessageDialog(this, "Envido!", "Computador Disse:", 1, null);
                            envido();
                        } else if (playerComputador.getCartas().getEnvido() > 24 && playerComputador.getCartas().getEnvido() < 31) {
                            JOptionPane.showMessageDialog(this, "Envido!", "Computador Disse:", 1, null);
                            envido();
                        } else if (playerComputador.getCartas().getEnvido() > 30 && getPtsRestantes(TrucoData.COMPUTADOR) < 5) {
                            JOptionPane.showMessageDialog(this, "Falta Envido!", "Computador Disse:", 1, null);
                            faltaEnvido();
                        } else if (playerComputador.getCartas().getEnvido() > 30) {
                            JOptionPane.showMessageDialog(this, "Real Envido!", "Computador Disse:", 1, null);
                            realEnvido();
                        }
                    }
                }
            }
           /* if ((estadoAtualEnvido.getClass() == EstadoInicialEnvido.class || estadoAtualEnvido.getClass() == EstadoFinalEnvido.class) &&
                    (estadoAtualFlor.getClass() == EstadoInicialFlor.class || estadoAtualFlor.getClass() == EstadoFinalFlor.class)) {*/
            if (playerAtual == TrucoData.COMPUTADOR) {
                if (isTrucoPedido) {
                    if (playerCantouTruco == TrucoData.HUMANO) {
                        if (estadoAtualTruco.getClass() == Truco.class) {
                            if (getSeqMaoRodada0() == 1 || getSeqMaoRodada0() == 2 || getSeqMaoRodada0() == 89){
                                JOptionPane.showMessageDialog(this, "Retruco!", "Computador Disse:", 1, null);
                                retruco();
                            } else if ((getSeqMaoRodada0() > 2 && getSeqMaoRodada0() < 79) ||
                                    (getSeqMaoRodada0() > 89 && getSeqMaoRodada0() < 155) ||
                                    (getSeqMaoRodada0() > 164 && getSeqMaoRodada0() < 220) ||
                                    (getSeqMaoRodada0() > 229 && getSeqMaoRodada0() < 275) ) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else if (getSeqMaoRodada0() == 88 || getSeqMaoRodada0() == 164 || getSeqMaoRodada0() == 229 ||
                                    getSeqMaoRodada0() == 284 || getSeqMaoRodada0() == 338 || getSeqMaoRodada0() == 339 ||
                                    getSeqMaoRodada0() == 384 || getSeqMaoRodada0() == 419 || getSeqMaoRodada0() == 447 ||
                                    getSeqMaoRodada0() == 468 || (getSeqMaoRodada0() > 482 && getSeqMaoRodada0() < 503)) {
                                JOptionPane.showMessageDialog(this, "Me vou ao Baralho!", "Computador Disse:", 1, null);
                                irBaralho();
                            }
                            else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }

                        } else if (estadoAtualTruco.getClass() == Vale4.class) {
                            if ((getSeqMaoRodada0() > 0 && getSeqMaoRodada0() < 5) ||
                                    (getSeqMaoRodada0() > 12 && getSeqMaoRodada0() < 16) ||
                                    getSeqMaoRodada0() == 24 || getSeqMaoRodada0() == 25 ||
                                    getSeqMaoRodada0() == 34 || getSeqMaoRodada0() == 35 ||
                                    (getSeqMaoRodada0() > 88 && getSeqMaoRodada0() < 92) ||
                                    getSeqMaoRodada0() == 100 || getSeqMaoRodada0() == 101 ||
                                    getSeqMaoRodada0() == 110 || getSeqMaoRodada0() == 111 ||
                                    getSeqMaoRodada0() == 165 || getSeqMaoRodada0() == 166 ||
                                    getSeqMaoRodada0() == 175 || getSeqMaoRodada0() == 176) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        }
                    } else {
                        if (estadoAtualTruco.getClass() == Retruco.class) {
                            if (getSeqMaoRodada0() == 1 || getSeqMaoRodada0() == 2 || getSeqMaoRodada0() == 89){
                                JOptionPane.showMessageDialog(this, "Vale 4!", "Computador Disse:", 1, null);
                                vale4();
                            } else if ((getSeqMaoRodada0() > 2 && getSeqMaoRodada0() < 79) ||
                                    (getSeqMaoRodada0() > 89 && getSeqMaoRodada0() < 155) ||
                                    (getSeqMaoRodada0() > 164 && getSeqMaoRodada0() < 220) ||
                                    (getSeqMaoRodada0() > 229 && getSeqMaoRodada0() < 275) ) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        } else {
                            jogar(cartaComputador());
                        }
                    }
                } else if (!isTrucoPedido && estadoAtualTruco.getClass() == EstadoInicialTruco.class) {
                    if (getSeqMaoRodada0() == 1 || getSeqMaoRodada0() == 2 || getSeqMaoRodada0() == 89){
                        JOptionPane.showMessageDialog(this, "Truco!", "Computador Disse:", 1, null);
                        truco();
                    } else if (getSeqMaoRodada0() == 88 || getSeqMaoRodada0() == 164 || getSeqMaoRodada0() == 229 ||
                            getSeqMaoRodada0() == 284 || getSeqMaoRodada0() == 338 || getSeqMaoRodada0() == 339 ||
                            getSeqMaoRodada0() == 384 || getSeqMaoRodada0() == 419 || getSeqMaoRodada0() == 447 ||
                            getSeqMaoRodada0() == 468 || (getSeqMaoRodada0() > 482 && getSeqMaoRodada0() < 503)) {
                        JOptionPane.showMessageDialog(this, "Me vou ao Baralho!", "Computador Disse:", 1, null);
                        irBaralho();
                    } else {
                        jogar(cartaComputador());
                    }
                }

            }

        } else if (mao.getRodadasJogadas() == 1) {
            //TODO IMPLEMENTAR GET RESULTADO 1ª RODADA
            //if(computador venceu 1ª Rodada) {

            if (!mao.isMaoConcluida()) {
                if (isTrucoPedido) {
                    if (playerCantouTruco == TrucoData.HUMANO) {
                        if (estadoAtualTruco.getClass() == Truco.class) {
                            if (getSeqMaoRodada1() == 1 || getSeqMaoRodada1() == 2 || getSeqMaoRodada1() == 3 ||
                                    getSeqMaoRodada1() == 14 || getSeqMaoRodada1() == 15 || getSeqMaoRodada1() == 26) {
                                JOptionPane.showMessageDialog(this, "Retruco!", "Computador Disse:", 1, null);
                                retruco();
                            } else if ((getSeqMaoRodada1() > 3 && getSeqMaoRodada1() < 10) ||
                                    (getSeqMaoRodada1() > 15 && getSeqMaoRodada1() < 22) ||
                                    (getSeqMaoRodada1() > 26 && getSeqMaoRodada1() < 33) ||
                                    (getSeqMaoRodada1() > 36 && getSeqMaoRodada1() < 43) ||
                                    (getSeqMaoRodada1() > 46 && getSeqMaoRodada1() < 53) ||
                                    (getSeqMaoRodada1() > 56 && getSeqMaoRodada1() < 62)) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else if (getSeqMaoRodada1() > 87 && getSeqMaoRodada1() < 102) {
                                JOptionPane.showMessageDialog(this, "Me vou ao Baralho!", "Computador Disse:", 1, null);
                                irBaralho();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }

                        } else if (estadoAtualTruco.getClass() == Vale4.class) {
                            if ((getSeqMaoRodada1() > 0 && getSeqMaoRodada1() < 5) ||
                                    (getSeqMaoRodada1() > 13 && getSeqMaoRodada1() < 17) ||
                                    getSeqMaoRodada1() == 26 || getSeqMaoRodada1() == 27 ||
                                    getSeqMaoRodada1() == 37 || getSeqMaoRodada1() == 47) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        }
                    } else {
                        if (estadoAtualTruco.getClass() == Retruco.class) {
                            if (getSeqMaoRodada1() == 1 || getSeqMaoRodada1() == 2 || getSeqMaoRodada1() == 3 ||
                                    getSeqMaoRodada1() == 14 || getSeqMaoRodada1() == 15 || getSeqMaoRodada1() == 26) {
                                JOptionPane.showMessageDialog(this, "Vale 4!", "Computador Disse:", 1, null);
                                vale4();
                            } else if ((getSeqMaoRodada1() > 3 && getSeqMaoRodada1() < 10) ||
                                    (getSeqMaoRodada1() > 15 && getSeqMaoRodada1() < 22) ||
                                    (getSeqMaoRodada1() > 26 && getSeqMaoRodada1() < 33) ||
                                    (getSeqMaoRodada1() > 36 && getSeqMaoRodada1() < 43) ||
                                    (getSeqMaoRodada1() > 46 && getSeqMaoRodada1() < 53) ||
                                    (getSeqMaoRodada1() > 56 && getSeqMaoRodada1() < 62)) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        } else {
                            jogar(cartaComputador());
                        }
                    }

                } else { //se truco nao foi pedido
                    if ((getSeqMaoRodada1() > 0 && getSeqMaoRodada1() < 6) ||
                            (getSeqMaoRodada1() > 13 && getSeqMaoRodada1() < 18) ||
                            (getSeqMaoRodada1() > 25 && getSeqMaoRodada1() < 29) ||
                            getSeqMaoRodada1() == 37 || getSeqMaoRodada1() == 38) {
                        JOptionPane.showMessageDialog(this, "Truco!", "Computador Disse:", 1, null);
                        truco();
                    } else {
                        jogar(cartaComputador());
                    }
                }
            }


            //} else { //se perdeu a 1ª Rodada

            //}
        } else {
            if (!mao.isMaoConcluida()) {
                if (isTrucoPedido) {
                    if (playerCantouTruco == TrucoData.HUMANO) {
                        if (estadoAtualTruco.getClass() == Truco.class) {
                            if (playerComputador.getCartas().getCartas().get(0).getHierarquia() < 5) {
                                JOptionPane.showMessageDialog(this, "Retruco!", "Computador Disse:", 1, null);
                                retruco();
                            } else if (playerComputador.getCartas().getCartas().get(0).getHierarquia() > 4 &&
                                    playerComputador.getCartas().getCartas().get(0).getHierarquia() < 8) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        } else if (estadoAtualTruco.getClass() == Vale4.class) {
                            if (playerComputador.getCartas().getCartas().get(0).getHierarquia() > 0 &&
                                    playerComputador.getCartas().getCartas().get(0).getHierarquia() < 7) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        }
                    } else {
                        if (estadoAtualTruco.getClass() == Retruco.class) {
                            if (playerComputador.getCartas().getCartas().get(0).getHierarquia() < 5) {
                                JOptionPane.showMessageDialog(this, "Vale 4!", "Computador Disse:", 1, null);
                                vale4();
                            } else if (playerComputador.getCartas().getCartas().get(0).getHierarquia() > 4 &&
                                    playerComputador.getCartas().getCartas().get(0).getHierarquia() < 7) {
                                JOptionPane.showMessageDialog(this, "Quero!", "Computador Disse:", 1, null);
                                queroTruco();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não Quero!", "Computador Disse:", 1, null);
                                naoQueroTruco();
                            }
                        }
                    }
                } else { // Se truco não foi pedido
                    if (playerComputador.getCartas().getCartas().get(0).getHierarquia() < 6) {
                        JOptionPane.showMessageDialog(this, "Truco!", "Computador Disse:", 1, null);
                        truco();
                    }
                }
            }
        }
    }


    public Carta cartaComputador() {

        Carta carta = null;
        if (mao.getRodadasJogadas() == 0) {
            if (cartasNaMesa.size() == 0) {
                if (playerComputador.getHierarquiaCartaAlta() < 5 && playerComputador.getHierarquiaCartaMedia() < 9) {
                    carta = playerComputador.getCartaByHierarquia(playerComputador.getHierarquiaCartaMedia());
                } else if (playerComputador.getHierarquiaCartaAlta() > 4) {
                    carta = playerComputador.getCartaByHierarquia(playerComputador.getHierarquiaCartaAlta());
                } else {
                    carta = playerComputador.getCartaByHierarquia(playerComputador.getHierarquiaCartaBaixa());
                }
            } else {
                if (playerComputador.getCartaParaVencerRodada(cartasNaMesa.getFirst().getHierarquia()) != null) {
                    carta = playerComputador.getCartaParaVencerRodada(cartasNaMesa.getFirst().getHierarquia());
                } else {
                    carta =  playerComputador.getMenorCarta(playerComputador.getCartas());
                }
            }

        } else if (mao.getRodadasJogadas() == 1) {
            if (cartasNaMesa.size() == 0) {
                if (vencedorRodada1 == Resultado.COMPUTADOR) {
                    carta =  playerComputador.getMenorCarta(playerComputador.getCartas());
                } else {
                    carta =  playerComputador.getMaiorCarta(playerComputador.getCartas());
                }

            } else {
                if (playerComputador.getCartaParaVencerRodada(cartasNaMesa.getFirst().getHierarquia()) != null) {
                    carta = playerComputador.getCartaParaVencerRodada(cartasNaMesa.getFirst().getHierarquia());
                } else {
                    carta =  playerComputador.getMenorCarta(playerComputador.getCartas());
                }
            }

        } else {
            carta = playerComputador.getCartas().getCartas().get(0);
        }

        return carta;
    }

    public void jogar(Carta carta) {


        if (playerAtual == TrucoData.HUMANO)
            cartasNaMesa.add(playerHumano.jogarCarta(carta));
        else {
            cartasNaMesa.add(playerComputador.jogarCarta(carta));
            jogaCartaComputador(carta);
        }
        passaVez();

        if (cartasNaMesa.size() == 2 && !mao.isMaoConcluida()) {

            Resultado resultadoRodada = getVencedorRodada(cartasNaMesa);
            setaVencedorRodada(resultadoRodada, mao.getRodadasJogadas());
            mao.fluxo(resultadoRodada);


            switch (resultadoRodada) {
                case EMPATE:
                    estadoRodada = isHumanoMao ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
                    playerAtual = isHumanoMao ? TrucoData.HUMANO : TrucoData.COMPUTADOR;
                    setStatus(playerAtual);
                    break;
                case HUMANO:
                    playerAtual = TrucoData.HUMANO;
                    estadoRodada = TrucoData.HUMANO_VEZ;
                    setStatus(playerAtual);
                    break;
                case COMPUTADOR:
                    playerAtual = TrucoData.COMPUTADOR;
                    estadoRodada = TrucoData.COMPUTADOR_VEZ;
                    setStatus(playerAtual);
                    break;

               /* default:
                    Carta cartaMaisAlta = this.getCartaMaisAlta(cartasNaMesa);
                    playerAtual = playerHumano.getCartasJogadas().getCartas().contains(cartaMaisAlta) ? TrucoData.HUMANO : TrucoData.COMPUTADOR;
                    estadoRodada = playerHumano.getCartasJogadas().getCartas().contains(cartaMaisAlta) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
                    setStatus(playerAtual);
                    break;*/
            }
            cartasNaMesa = new LinkedList<Carta>();
            //estadoRodada = TrucoData.FIM_RODADA;
        }
        if (playerAtual == TrucoData.HUMANO) {

            if (mao.getRodadasJogadas() == 0) {
                if (estadoAtualEnvido.getClass() == EstadoInicialEnvido.class &&
                        estadoAtualFlor.getClass() == EstadoInicialFlor.class &&
                        estadoAtualTruco.getClass() == EstadoInicialTruco.class) {
                    stateButtonsInicial();
                } else if (estadoAtualTruco.getClass() == EstadoInicialTruco.class) {
                    enableDisableAllButtons(false);
                    btnTruco.setEnabled(true);
                    btnIrBaralho.setEnabled(true);
                    enableDisableCartas(true);
                } else {
                    enableDisableCartas(true);
                    btnIrBaralho.setEnabled(true);
                }

            } /*else if (mao.getRodadasJogadas() == 1) {
                if (estadoAtualTruco.getClass() == EstadoInicialTruco.class) {
                    enableDisableAllButtons(false);
                    btnTruco.setEnabled(true);
                    btnIrBaralho.setEnabled(true);
                    enableDisableCartas(true);
                } else {
                    enableDisableCartas(true);
                    btnIrBaralho.setEnabled(true);
                }

            }*/ else {
                if (estadoAtualTruco.getClass() == EstadoInicialTruco.class) {
                    enableDisableAllButtons(false);
                    btnTruco.setEnabled(true);
                    btnIrBaralho.setEnabled(true);
                    enableDisableCartas(true);
                } else {
                    enableDisableCartas(true);
                    btnIrBaralho.setEnabled(true);
                }
            }
        }
        isMaoConcluida = false;
        if (mao.isMaoConcluida()) {
            int ganhador = mao.getVencedorMao();
            if (ganhador ==TrucoData.HUMANO)
                playerHumano.somarPontos(estadoAtualTruco.getPontosAceitos());
            else
                playerComputador.somarPontos(estadoAtualTruco.getPontosAceitos());

            this.isMaoConcluida = true;
            estadoPartida = TrucoData.NOVA_MAO;

        }

        atualizaPontuacao();
        if (isPartidaConcluida())
            estadoPartida = TrucoData.FIM_PARTIDA;
    }

    public void setaVencedorRodada(Resultado resultadoRodada, int numRodada) {
        switch (numRodada) {
            case 0:
                vencedorRodada1 = resultadoRodada;
                break;
            case 1:
                vencedorRodada2 = resultadoRodada;
                break;
            case 2:
                vencedorRodada3 = resultadoRodada;
                break;
            case 3:
                break;
        }
    }

    private void initValuesNewMao() {

        isHumanoMao = !isHumanoMao;
        estadoRodada = isHumanoMao ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        playerAtual = isHumanoMao ? TrucoData.HUMANO : TrucoData.COMPUTADOR;
        if (playerAtual == TrucoData.HUMANO) {
            stateButtonsInicial();
        } else {
            enableDisableAllButtons(false);
            enableDisableCartas(false);
        }

        setStatus(playerAtual);
        isFlorCantada = false;
        playerCantouFlor = -1;
        estadoAtualFlor = new EstadoInicialFlor();
        isEnvidoPedido = false;
        playerCantouEnvido = -1;
        estadoAtualEnvido = new EstadoInicialEnvido();
        isTrucoPedido = false;
        playerCantouTruco = -1;
        estadoAtualTruco = new EstadoInicialTruco();
        mao = new Mao();

        if (pnlCartas != null) {
            pnlCartas.removeAll();
            remove(pnlCartas);
        }

        baralho.embaralhar2();
        JOptionPane.showMessageDialog(this, "Dar as Cartas!!", "Nova Mão", 1, null);
        darAsCartas();
        setOrderCartas();
        add(createCartasPanel(), BorderLayout.CENTER);
        pnlCartas.repaint();
        invalidate();
        validate();
        pnlCartas.invalidate();
        pnlCartas.validate();

        vencedorRodada1 = Resultado.EMPATE;
        vencedorRodada2 = Resultado.EMPATE;
        vencedorRodada3 = Resultado.EMPATE;

    }

    public Carta getCartaMaisAlta(LinkedList<Carta> cartas) {
        if (cartas.getFirst().getHierarquia() < cartas.getLast().getHierarquia())
            return cartas.getFirst();
        else
            return cartas.getLast();
    }

    //TODO
    public int getVencedorFlor() {

        if (playerComputador.getFlor() > playerHumano.getFlor())
            return TrucoData.COMPUTADOR;
        else if (playerHumano.getFlor() > playerComputador.getFlor())
            return TrucoData.HUMANO;
        else
            return isHumanoMao ? TrucoData.HUMANO : TrucoData.COMPUTADOR;

    }

    public int getVencedorEnvido() {

        if (playerComputador.getEnvido() > playerHumano.getEnvido())
            return TrucoData.COMPUTADOR;
        else if (playerHumano.getEnvido() > playerComputador.getEnvido())
            return TrucoData.HUMANO;
        else
            return isHumanoMao ? TrucoData.HUMANO : TrucoData.COMPUTADOR;
    }

    public Resultado getVencedorRodada(LinkedList<Carta> cartas) {

        Carta cartaMaisAlta = cartas.getFirst();
        Boolean isEmpate = false;

        if (cartas.getFirst().getHierarquia() > cartas.getLast().getHierarquia()) {
            cartaMaisAlta = cartas.getLast();
            isEmpate = false;
        } else if (cartas.getFirst().getHierarquia() == cartas.getLast().getHierarquia())
            isEmpate = true;

        if (!isEmpate) {
            if (playerHumano.getCartasJogadas().getCartas().contains(cartaMaisAlta))
                return Resultado.HUMANO;
            else
                return Resultado.COMPUTADOR;

        } else {
            return Resultado.EMPATE;
        }

    }

    public void createBaralho() {

        baralho = new Baralho();
        baralho.criarBaralho();
        System.out.println(baralho.toString());
        cartasHumano = new Baralho();
        cartasComputador = new Baralho();
    }

    public void darAsCartas() {

        cartasComputador.devolverCartasMonte();
        cartasHumano.devolverCartasMonte();

        if (isHumanoMao) {
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 0) {
                    cartasHumano.draw(baralho, i);
                } else {
                    cartasComputador.draw(baralho, i);
                }
            }
        } else {
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 0) {
                    cartasComputador.draw(baralho, i);
                } else {
                    cartasHumano.draw(baralho, i);
                }
            }
        }


        System.out.println("\n Cartas Humano...");
        System.out.println(cartasHumano.toString());
        System.out.println("\n Cartas Computador...");
        System.out.println(cartasComputador.toString());

    }

    public JPanel createCartasPanel () {

        pnlCartas = new JPanel(new MigLayout());

        pnlCartas.setOpaque(false);
        pnlCartas.setPreferredSize(new Dimension(400,600));

        pnlCartasComp = new JPanel(new MigLayout("fill"));
        for (Carta carta : cartasComputador.getCartas()) {
            CardLabel card = new CardLabel(cartasComputador.getVersoCarta(), carta);
            card.setSize(card.getPreferredSize());
            pnlCartasComp.add(card, "split, span, center");
        }
        pnlCartasComp.setOpaque(false);
        pnlCartasComp.setPreferredSize(new Dimension(400, 100));


        pnlCartasMao = new JPanel(new MigLayout("fill", "grow"));
        pnlRodada1 = new RoundPanel();
        pnlRodada1.setOpaque(false);
        pnlRodada1.setPreferredSize(new Dimension(100, 360));
        pnlRodada2 = new RoundPanel();
        pnlRodada2.setOpaque(false);
        pnlRodada2.setPreferredSize(new Dimension(100, 360));
        pnlRodada3 = new RoundPanel();
        pnlRodada3.setOpaque(false);
        pnlRodada3.setPreferredSize(new Dimension(100, 360));

        pnlCartasMao.add(pnlRodada1, "center, growx, growy");
        pnlCartasMao.add(pnlRodada2, "center, growx, growy");
        pnlCartasMao.add(pnlRodada3, "center, growx, growy");
        pnlCartasMao.setOpaque(false);
        pnlCartasMao.setPreferredSize(new Dimension(400, 360));

        pnlCartasHumano = new JPanel(new MigLayout("fill"));
        for (Carta carta : cartasHumano.getCartas()) {
            CardLabel card = new CardLabel(carta.getCartaImg(), carta);
            card.setSize(card.getPreferredSize());
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            card.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (!card.isEnabled()) {
                        return;
                    }

                    if(e.getClickCount() == 2) {
                        card.setLocation(33,77);
                        card.removeMouseListener(this);
                        card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            card.setIcon(cartasHumano.getVersoCarta());
                        }

                        if (!pnlRodada1.isExisteCartaHumano()) {
                            pnlRodada1.add(card);
                            pnlRodada1.setExisteCartaHumano(true);
                            pnlRodada1.setCartaHumano(carta);
                            pnlRodada1.ordenaCartasJogadas();

                        } else if (!pnlRodada2.isExisteCartaHumano()) {
                            pnlRodada2.add(card);
                            pnlRodada2.setExisteCartaHumano(true);
                            pnlRodada2.setCartaHumano(carta);
                            pnlRodada2.ordenaCartasJogadas();
                        } else {
                            pnlRodada3.add(card);
                            pnlRodada3.setExisteCartaHumano(true);
                            pnlRodada3.setCartaHumano(carta);
                            pnlRodada3.ordenaCartasJogadas();
                        }

                        jogar(card.getCarta());
                        atualizaPontuacao();

                        if (isPartidaConcluida())
                            estadoPartida = TrucoData.FIM_PARTIDA;

                        if (isMaoConcluida) {
                            //TODO testa se concluiu a mao, se sim seta nova mao
                        }

                        System.out.println("Humano jogou na Mao " + numRodada);
                        // estadoRodada = TrucoData.COMPUTADOR_VEZ;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            pnlCartasHumano.add(card, "split, span, center");
        }
        pnlCartasHumano.setOpaque(false);
        pnlCartasHumano.setPreferredSize(new Dimension(400, 100));

        pnlCartas.add(pnlCartasComp, "wrap");
        pnlCartas.add(pnlCartasMao, "wrap");
        pnlCartas.add(pnlCartasHumano);

        return pnlCartas;
    }

    public JPanel createActionPanel(){

        pnlActions = new JPanel(new MigLayout());
        pnlActions.setOpaque(false);
        //TODO testar modificação no windows
        pnlActions.setPreferredSize(new Dimension(201,600));

        pnlPts = new JPanel(new MigLayout("fill"));
        pnlPts.setBorder(BorderFactory.createLineBorder(Color.white));
        lblPtsHumano = new JLabel("<html><font color='white'>Humano: " + ptsHumano + " </font></html>");
        lblPtsComp = new JLabel("<html><font color='white'>Computador: " + ptsComp + " </font></html>");
        pnlPts.add(lblPtsComp, "split, span, center");
        pnlPts.add(lblPtsHumano, "split, span, center");
        pnlPts.setOpaque(false);
        pnlPts.setPreferredSize(new Dimension(200, 60));

        pnlEnvido = new JPanel(new MigLayout("wrap", "[grow, fill, center]"));
        pnlEnvido.setBorder(BorderFactory.createLineBorder(Color.white));
        btnEnvido = new JButton("Envido");
        btnRealEnvido = new JButton("Real Envido");
        btnFaltaEnvido = new JButton("Falta Envido");
        pnlEnvido.add(btnEnvido, "growx");
        pnlEnvido.add(btnRealEnvido, "growx");
        pnlEnvido.add(btnFaltaEnvido, "growx");
        pnlEnvido.setOpaque(false);
        pnlEnvido.setPreferredSize(new Dimension(200, 90));

        pnlFlor = new JPanel(new MigLayout("wrap", "[grow, fill, center]"));
        pnlFlor.setBorder(BorderFactory.createLineBorder(Color.white));
        btnFlor = new JButton("Flor");
        btnContraFlor = new JButton("Contra-Flor");
        btnContraFlorResto = new JButton("Contra-Flor e Resto");
        pnlFlor.add(btnFlor, "growx");
        pnlFlor.add(btnContraFlor, "growx");
        pnlFlor.add(btnContraFlorResto, "growx");
        pnlFlor.setOpaque(false);
        pnlFlor.setPreferredSize(new Dimension(200, 90));

        pnlTruco = new JPanel(new MigLayout("wrap", "[grow, fill, center]"));
        pnlTruco.setBorder(BorderFactory.createLineBorder(Color.white));
        btnTruco = new JButton("Truco");
        btnRetruco = new JButton("Retruco");
        btnVale4 = new JButton("Vale 4");
        pnlTruco.add(btnTruco, "growx");
        pnlTruco.add(btnRetruco, "growx");
        pnlTruco.add(btnVale4, "growx");
        pnlTruco.setOpaque(false);
        pnlTruco.setPreferredSize(new Dimension(200, 90));

        pnlAceite = new JPanel(new MigLayout("wrap", "[grow, fill, center]"));
        pnlAceite.setBorder(BorderFactory.createLineBorder(Color.white));
        btnQuero = new JButton("QUERO");
        btnNaoQuero = new JButton("NÃO QUERO");
        pnlAceite.add(btnQuero, "growx");
        pnlAceite.add(btnNaoQuero, "growx");
        pnlAceite.setOpaque(false);
        pnlAceite.setPreferredSize(new Dimension(200, 60));

        pnlFuga = new JPanel(new MigLayout("wrap", "[grow, fill, center]"));
        pnlFuga.setBorder(BorderFactory.createLineBorder(Color.white));
        btnIrBaralho = new JButton("Ir para o Baralho");
        pnlFuga.add(btnIrBaralho, "growx");
        pnlFuga.setOpaque(false);
        pnlFuga.setPreferredSize(new Dimension(200, 30));

        btnEnvido.addActionListener(this);
        btnRealEnvido.addActionListener(this);
        btnFaltaEnvido.addActionListener(this);
        btnTruco.addActionListener(this);
        btnRetruco.addActionListener(this);
        btnVale4.addActionListener(this);
        btnFlor.addActionListener(this);
        btnContraFlor.addActionListener(this);
        btnContraFlorResto.addActionListener(this);
        btnQuero.addActionListener(this);
        btnNaoQuero.addActionListener(this);
        btnIrBaralho.addActionListener(this);

        pnlActions.add(pnlPts, "wrap");
        pnlActions.add(pnlEnvido, "wrap");
        pnlActions.add(pnlFlor, "wrap");
        pnlActions.add(pnlTruco, "wrap");
        pnlActions.add(pnlAceite, "wrap");
        pnlActions.add(pnlFuga);

        return pnlActions;
    }

    public void createPlayers() {

        playerHumano = new Player(cartasHumano);
        playerComputador = new Player(cartasComputador);
    }

    public boolean isPartidaConcluida() {
        boolean isPartidaConcluida = false;
        if (playerComputador.getPontos() >= 24 || playerHumano.getPontos() >=24) {
            isPartidaConcluida = true;
            estadoPartida = TrucoData.FIM_PARTIDA;
        }


        return isPartidaConcluida;
    }

    public void setPartidaFinalizada() {

        String msg="";
        if (playerComputador.getPontos() > playerHumano.getPontos())
            msg = " Computador venceu!!!";
        else
            msg = "Parabéns você Venceu!!!";
        JOptionPane.showMessageDialog(this, msg, "Partida Finalizada!", 1, null);
    }

    public void iniciarContagem() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSegundo++;

                if(currentSegundo==60){
                    currentMinuto++;
                    currentSegundo = 0;
                }

                if(currentMinuto==60){
                    currentHora++;
                    currentMinuto = 0;
                }

                String hr = currentHora <= 9? "0"+currentHora:currentHora+"";
                String min = currentMinuto <= 9? "0"+currentMinuto:currentMinuto+"";
                String seg = currentSegundo <= 9? "0"+currentSegundo:currentSegundo+"";

                lblClock.setText("Tempo: " + hr+":"+min+":"+seg);
            }
        };
        this.timer = new Timer(velocidade, action);
        this.timer.start();
    }

    public void atualizaPontuacao() {

        int ptsComp = playerComputador.getPontos();
        int ptsHumano = playerHumano.getPontos();

        lblPtsComp.setText("<html><font color='white'>Computador: " + ptsComp + " </font></html>");
        lblPtsHumano.setText("<html><font color='white'>Humano: " + ptsHumano + " </font></html>");
    }

    public void sair(){
        int option = JOptionPane.showConfirmDialog(null,"Tem certeza que quer sair?",
                "Truco CBR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.YES_OPTION)
            System.exit(0);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void setStatus(int playerAtual) {
        String playerJoga = playerAtual == TrucoData.HUMANO ? "Humano" : "Computador";

        if (isHumanoMao)
            lblInfo.setText("Humano é o mão :::: Jogador que joga: " + playerJoga);
        else
            lblInfo.setText("Computador é o mão :::: Jogador que joga: " + playerJoga);
    }

    public void setOrderCartas() {

        List<Integer> hierarquias = new ArrayList();
        for (Carta carta : playerComputador.getCartas().getCartas())
            hierarquias.add(carta.getHierarquia());

        Collections.sort(hierarquias);
        playerComputador.setHierarquiaCartaAlta(hierarquias.get(0));
        playerComputador.setHierarquiaCartaBaixa(hierarquias.get(1));
        playerComputador.setHierarquiaCartaBaixa(hierarquias.get(2));
    }

    public void stateButtonsInicial() {

        enableDisableAllButtons(false);
        btnFlor.setEnabled((playerHumano.getFlor() > 0)? true : false);
        enableDisableButtons(buttonsEnvido, true);
        btnTruco.setEnabled(true);
        btnIrBaralho.setEnabled(true);
        enableDisableCartas(true);
    }

    public void enableDisableButtons(boolean isEnable) {
        btnEnvido.setEnabled(isEnable);
        btnRealEnvido.setEnabled(isEnable);
        btnFaltaEnvido.setEnabled(isEnable);
        btnTruco.setEnabled(isEnable);
        btnRetruco.setEnabled(isEnable);
        btnVale4.setEnabled(isEnable);
        btnFlor.setEnabled(isEnable);
        btnContraFlor.setEnabled(isEnable);
        btnContraFlorResto.setEnabled(isEnable);
        btnQuero.setEnabled(isEnable);
        btnNaoQuero.setEnabled(isEnable);
        btnIrBaralho.setEnabled(isEnable);
    }

    public void enableDisableButtons(List<JButton> buttons, boolean isEnable) {
        for (JButton btn : buttons) {
            btn.setEnabled(isEnable);
        }
    }

    public void enableDisableAllButtons(boolean isEnable) {
        btnIrBaralho.setEnabled(isEnable);
        enableDisableButtons(buttonsFlor, isEnable);
        enableDisableButtons(buttonsEnvido, isEnable);
        enableDisableButtons(buttonsTruco, isEnable);
        enableDisableButtons(buttonsAceite, isEnable);
    }

    public void enableDisableCartas(boolean isEnable) {

        Component components[] = pnlCartasHumano.getComponents();
        for (Component component : components) {
            if (component instanceof CardLabel) {
                component.setEnabled(isEnable);
            }
        }
    }

    public int getPtsRestantes(int jogador){
        int falta = 24;
        if (jogador == 0) {
            if (playerComputador.getPontos() < 24)
                falta = 24 - playerComputador.getPontos();
        } else {
            if (playerHumano.getPontos() < 24)
                falta = 24 - playerHumano.getPontos();
        }

        return falta;
    }

    public void passaVez() {
        playerAtual = (playerAtual == TrucoData.HUMANO) ? TrucoData.COMPUTADOR : TrucoData.HUMANO;
        estadoRodada = (playerAtual == TrucoData.HUMANO) ?  TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);
    }

    public int getDiffPontos() {
        int diff = 0;
        if (ptsHumano > ptsComp)
            diff = ptsHumano - ptsComp;
        return diff;
    }

    public int getSeqMaoRodada0() {

        int seq = 0;
        try {

            JSONParser parser = new JSONParser();
            InputStream inputStream = loader.getResourceAsStream("br/ufsm/trucocbr/res/maosRod0.json");

            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(inputStream));
            for (Object object : jsonArray) {

                JSONObject mao = (JSONObject) object;
                int h1 = (int) (long) mao.get("h1");
                int h2 = (int) (long) mao.get("h2");
                int h3 = (int) (long) mao.get("h3");
                List<Integer> hierarquias = playerComputador.getOrderCartas();
                if (hierarquias.size() == 3) {
                    if (h1 == hierarquias.get(0) && h2 == hierarquias.get(1) &&
                            h3 == hierarquias.get(2)) {
                        seq = (int) (long) mao.get("seq");
                    }
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return seq;
    }

    public int getSeqMaoRodada1() {

        int seq = 0;
        try {

            JSONParser parser = new JSONParser();
            InputStream inputStream = loader.getResourceAsStream("br/ufsm/trucocbr/res/maosRod1.json");
            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(inputStream));
            for (Object object : jsonArray) {

                JSONObject mao = (JSONObject) object;
                int h1 = (int) (long) mao.get("h1");
                int h2 = (int) (long) mao.get("h2");
                List<Integer> hierarquias = playerComputador.getOrderCartas();
                if (hierarquias.size() == 2) {
                    if (h1 == hierarquias.get(0) && h2 == hierarquias.get(1)) {
                        seq = (int) (long) mao.get("seq");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return seq;
    }

    public void jogaCartaComputador(Carta carta) {

        Component components[] = pnlCartasComp.getComponents();
        for (Component component : components) {
            if (component instanceof CardLabel && ((CardLabel) component).getCarta() == carta) {
                //component.setLocation(2,2);
                ((CardLabel) component).setIcon( ((CardLabel) component).getCarta().getCartaImg());
                if (!pnlRodada1.isExisteCartaComp()) {
                    pnlRodada1.add(component);
                    pnlRodada1.setExisteCartaComp(true);
                    pnlRodada1.setCartaComputador(carta);
                    pnlRodada1.ordenaCartasJogadas();

                } else if (!pnlRodada2.isExisteCartaComp()) {
                    pnlRodada2.add(component);
                    pnlRodada2.setExisteCartaComp(true);
                    pnlRodada2.setCartaComputador(carta);
                    pnlRodada2.ordenaCartasJogadas();
                } else {
                    pnlRodada3.add(component);
                    pnlRodada3.setExisteCartaComp(true);
                    pnlRodada3.setCartaComputador(carta);
                    pnlRodada3.ordenaCartasJogadas();
                }
                component.setLocation(2,2);
            }
        }
        pnlCartasComp.repaint();
        invalidate();
        validate();
        pnlCartasComp.invalidate();
        pnlCartasComp.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == jmiSair){
            sair();
        } else if (source == jmiNovoJogo) {
            if (pnlOptions == null)
                pnlOptions = new OptionsPanel(this);

            pnlOptions.setVisible(true);
        } else if (source == jMenuAjuda) {

            //TODO menu ajuda
            System.out.println("Ajuda");

        } else if (source == btnEnvido) {
            playerCantouEnvido = playerAtual;
            envido();
            enableDisableButtons(false);
            enableDisableCartas(false);
            System.out.println("Humano cantou envido!");

        } else if (source == btnRealEnvido) {
            playerCantouEnvido = playerAtual;
            realEnvido();
            enableDisableButtons(false);
            enableDisableCartas(false);
            System.out.println("Humano cantou Real Envido!");


        } else if (source == btnFaltaEnvido) {
            playerCantouEnvido = playerAtual;
            faltaEnvido();
            enableDisableButtons(false);
            enableDisableCartas(false);
            System.out.println("Humano cantou Falta Envido!");

        } else if (source == btnFlor) {
            flor();
        } else if (source == btnContraFlor) {
            contraFlor();
        } else if (source == btnContraFlorResto) {
            contraFlorAlResto();
        } else if (source == btnTruco) {
            playerCantouTruco = playerAtual;
            truco();
            enableDisableButtons(false);
            enableDisableCartas(false);
            System.out.println("Humano cantou Truco!");

        } else if (source == btnRetruco) {
            playerCantouEnvido = playerAtual;
            retruco();


        } else if (source == btnVale4) {
            vale4();

        } else if (source == btnIrBaralho) {
            irBaralho();
        } else if (source == btnQuero) {

            if (isEnvidoPedido && !(estadoAtualEnvido.getClass() == EstadoFinalEnvido.class)) {
                queroEnvido();
            }

            if (isTrucoPedido) {
                queroTruco();
            }

            if (isFlorCantada) {
                queroFlor();
            }




        } else if (source == btnNaoQuero) {
            if (isEnvidoPedido && !(estadoAtualEnvido.getClass() == EstadoFinalEnvido.class)) {
                naoQueroEnvido();
            }

            if (isTrucoPedido) {
                naoQueroTruco();
            }

            if (isFlorCantada) {
                naoQueroFlor();
            }

        }
    }



    // Flor
    //TODO Testar se esta na primeira rodada e testar se tem pontos

    public void flor() {
        playerCantouFlor = playerAtual;
        isFlorCantada = true;
        estadoAtualFlor = new Flor(estadoAtualFlor);
        if (isEnvidoPedido) {
            estadoAtualEnvido = new EstadoFinalEnvido(estadoAtualEnvido);
        }
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnQuero.setEnabled(true);
            btnContraFlor.setEnabled(playerHumano.getFlor() > 0 ? true : false);
            btnContraFlorResto.setEnabled(playerHumano.getFlor() > 0 ? true : false);
            //btnTruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
        } else {
            enableDisableAllButtons(false);
            enableDisableCartas(false);
        }
    }

    public void queroFlor() {
        int playerVencedorFlor = getVencedorFlor();
        if (playerVencedorFlor == TrucoData.COMPUTADOR)
            playerComputador.somarPontos(estadoAtualFlor.getPontosAceitos());
        else
            playerHumano.somarPontos(estadoAtualFlor.getPontosAceitos());

        atualizaPontuacao();

        if (isEnvidoPedido) {
            playerAtual = playerCantouEnvido;
            isEnvidoPedido = false;
        } else
            playerAtual = playerCantouFlor;

        estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);

        estadoAtualFlor = new EstadoFinalFlor(estadoAtualFlor);
        isFlorCantada = false;
        playerCantouFlor = -1;
        estadoAtualEnvido = new EstadoFinalEnvido(estadoAtualEnvido);
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnTruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
        }  else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    private int pontosRestantes(int playerVencedorFlor) {
        int pontosRestantes = 0;
        if (playerVencedorFlor == TrucoData.HUMANO)
            pontosRestantes = 24 - playerHumano.getPontos();
        else
            pontosRestantes = 24 - playerComputador.getPontos();

        return pontosRestantes;
    }


    public void naoQueroFlor() {

        if (!(estadoAtualFlor instanceof Flor)) {
            if (playerCantouFlor == TrucoData.HUMANO)
                playerHumano.somarPontos(estadoAtualFlor.getPontosNaoAceitos());
            else
                playerComputador.somarPontos(estadoAtualFlor.getPontosNaoAceitos());

            atualizaPontuacao();
            playerAtual = playerCantouFlor;
            estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
            setStatus(playerAtual);
            estadoAtualFlor = new EstadoFinalFlor(estadoAtualFlor);
            isFlorCantada = false;
            playerCantouFlor = -1;
            if (playerAtual == TrucoData.HUMANO) {
                enableDisableAllButtons(false);
                btnTruco.setEnabled(true);
                btnIrBaralho.setEnabled(true);
                enableDisableCartas(true);
            }  else {
                enableDisableCartas(false);
                enableDisableAllButtons(false);
            }
        }
    }

    //TODO Testar se esta na primeira rodada e testar se tem pontos
    public void contraFlor() {

        estadoAtualFlor = new ContraFlor(estadoAtualFlor);
        passaVez();

        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnContraFlorResto.setEnabled(true);
            enableDisableButtons(buttonsAceite, true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
        }  else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }


    public void contraFlorAlResto() {

        int playerVencedorFlor = getVencedorFlor();
        estadoAtualFlor = new ContraFlorResto(estadoAtualFlor, pontosRestantes(playerVencedorFlor));
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            enableDisableButtons(buttonsAceite, true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
        }  else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    // ENVIDO
    //TODO Testar se esta na primeira rodada e testar se tem pontos

    public void envido() {
        if (mao.getRodadasJogadas() == 0) {
            isEnvidoPedido = true;
            estadoAtualEnvido = new Envido(estadoAtualEnvido, estadoAtualFlor);

            if (playerCantouEnvido == -1) {
                playerCantouEnvido = playerAtual;
            }
            passaVez();
            if (playerAtual == TrucoData.HUMANO) {
                enableDisableAllButtons(false);
                enableDisableButtons(buttonsAceite, true);
                btnRealEnvido.setEnabled(true);
                btnFaltaEnvido.setEnabled(true);
                btnIrBaralho.setEnabled(true);
            }  else {
                enableDisableCartas(false);
                enableDisableAllButtons(false);
            }
        }

    }

    public void realEnvido() {

        isEnvidoPedido = true;
        estadoAtualEnvido = new RealEnvido(estadoAtualEnvido, estadoAtualFlor);
        if (playerCantouEnvido == -1) {
            playerCantouEnvido = playerAtual;
        }
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            enableDisableButtons(buttonsAceite, true);
            btnFaltaEnvido.setEnabled(true);
            btnIrBaralho.setEnabled(true);
        }  else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    public void queroEnvido() {

        int playerVencedorEnvido = getVencedorEnvido();
        String msg = "";
        if (playerVencedorEnvido == TrucoData.COMPUTADOR) {
            playerComputador.somarPontos(estadoAtualEnvido.getPontosAceitos());
            msg = "Computador Ganhou!! Computador " + playerComputador.getEnvido() + " X Humano " + playerHumano.getEnvido();
        } else {
            playerHumano.somarPontos(estadoAtualEnvido.getPontosAceitos());
            msg = "Humano Ganhou!! Humano " + playerHumano.getEnvido() + " X Computador " + playerComputador.getEnvido();
        }

        JOptionPane.showMessageDialog(this, msg, "Resultado", 1, null);

        atualizaPontuacao();
        playerAtual = playerCantouEnvido;
        estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);
        estadoAtualEnvido = new EstadoFinalEnvido(estadoAtualEnvido);
        isEnvidoPedido = false;
        playerCantouEnvido = -1;
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnTruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
        }  else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }

        return;
    }

    private int pontosRestantesAdversario(int playerVencedorFaltaEnvido) {

        int pontosRestantes = 0;
        if (playerVencedorFaltaEnvido == TrucoData.HUMANO)
            pontosRestantes = 24 - playerComputador.getPontos();
        else
            pontosRestantes = 24 - playerHumano.getPontos();

        return pontosRestantes;
    }

    //TODO Testar se esta na primeira rodada e testar se tem pontos
    public void faltaEnvido() {

        isEnvidoPedido = true;
        int playerVencedorEnvido = getVencedorEnvido();
        estadoAtualEnvido = new FaltaEnvido(estadoAtualEnvido, estadoAtualFlor,
                this.pontosRestantesAdversario(playerVencedorEnvido), this.pontosRestantes(playerVencedorEnvido));

        if (playerCantouEnvido == -1) {
            playerCantouEnvido = playerAtual;
        }
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            enableDisableButtons(buttonsAceite, true);
            btnIrBaralho.setEnabled(true);
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    public void naoQueroEnvido() {
        //passaVez();
        if (playerCantouEnvido == TrucoData.HUMANO && playerAtual == TrucoData.COMPUTADOR)
            playerHumano.somarPontos(estadoAtualEnvido.getPontosNaoAceitos());
        else
            playerComputador.somarPontos(estadoAtualEnvido.getPontosNaoAceitos());
        atualizaPontuacao();
        playerAtual = playerCantouEnvido;
        estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);
        estadoAtualEnvido = new EstadoFinalEnvido(estadoAtualEnvido);
        isEnvidoPedido = false;
        playerCantouEnvido = -1;
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnTruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
            return;
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    // TRUCO

    public void truco() {
        isTrucoPedido = true;
        playerCantouTruco = playerAtual;
        estadoAtualTruco = new Truco(estadoAtualTruco, estadoAtualEnvido, estadoAtualFlor);
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            enableDisableButtons(buttonsAceite, true);
            btnRetruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }

    }

    public void retruco() {

        estadoAtualTruco = new Retruco(estadoAtualTruco);
        if (!isTrucoPedido) {
            isTrucoPedido = true;
            playerCantouTruco = playerAtual;
        }
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            enableDisableButtons(buttonsAceite, true);
            btnVale4.setEnabled(true);
            btnIrBaralho.setEnabled(true);
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    public void vale4() {

        estadoAtualTruco = new Vale4(estadoAtualTruco);
        if (!isTrucoPedido) {
            isTrucoPedido = true;
            playerCantouTruco = playerAtual;
        }
        passaVez();
        if (playerAtual == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnIrBaralho.setEnabled(true);
            enableDisableButtons(buttonsAceite, true);
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }
    }

    public void queroTruco() {
        //playerCantouTruco = playerAtual;
        playerAtual = playerCantouTruco;
        estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);
        isTrucoPedido = false;
        if (playerAtual == TrucoData.HUMANO && playerCantouTruco == TrucoData.HUMANO) {
            enableDisableAllButtons(false);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);

        } else if (playerAtual == TrucoData.HUMANO && playerCantouTruco == TrucoData.COMPUTADOR) {
            enableDisableAllButtons(false);
            btnRetruco.setEnabled(true);
            btnIrBaralho.setEnabled(true);
            enableDisableCartas(true);
        } else {
            enableDisableCartas(false);
            enableDisableAllButtons(false);
        }

    }

    public void naoQueroTruco() {
        // playerCantouTruco = playerAtual;
        playerAtual = playerCantouTruco;
        estadoRodada = (playerAtual == TrucoData.HUMANO) ? TrucoData.HUMANO_VEZ : TrucoData.COMPUTADOR_VEZ;
        setStatus(playerAtual);
        int playerASomar = this.getJogadorNaoTruco();
        if (playerASomar == TrucoData.HUMANO)
            playerHumano.somarPontos(estadoAtualTruco.getPontosNaoAceitos());
        else
            playerComputador.somarPontos(estadoAtualTruco.getPontosNaoAceitos());


        atualizaPontuacao();
        //estadoAtualTruco = new EstadoInicialTruco();
        estadoAtualTruco = new EstadoFinalTruco(estadoAtualTruco);
        isTrucoPedido = false;
        initValuesNewMao();

        if (isPartidaConcluida()) {
            setPartidaFinalizada();
        } else {

        }
        //TODO encerrar Mao
    }

    private int getJogadorNaoTruco() {
        int playerPedido;
        playerPedido = playerCantouTruco;

        return playerPedido;
    }

    public void irBaralho() {
        playerFoiBaralho = playerAtual;
        passaVez();
        if (isTrucoPedido) {
            if (playerAtual == TrucoData.HUMANO)
                playerHumano.somarPontos(estadoAtualTruco.getPontosNaoAceitos());
            else
                playerComputador.somarPontos(estadoAtualTruco.getPontosNaoAceitos());
        } else {
            if (playerAtual == TrucoData.HUMANO)
                playerHumano.somarPontos(estadoAtualTruco.getPontosNaoAceitos());
            else
                playerComputador.somarPontos(estadoAtualTruco.getPontosAceitos());
        }
        atualizaPontuacao();
        int qtdeCartas = 0;
        if (playerAtual == TrucoData.HUMANO)
            qtdeCartas = playerHumano.getCartas().getCartas().size();
        else
            qtdeCartas = playerComputador.getCartas().getCartas().size();

        if (qtdeCartas == 3) {
            if (playerAtual == TrucoData.HUMANO)
                playerHumano.somarPontos(1);
            else
                playerComputador.somarPontos(1);

        }
        this.initValuesNewMao();

        if (isPartidaConcluida()) {
            setPartidaFinalizada();
        } else {

        }
        //TODO encerrar Mao
    }



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
            similarityTrucoMao1 = new SimilarityTrucoMao1();
            similarityEnvidoPe = new SimilarityEnvidoPe();
            similarityTrucoPe = new SimilarityTrucoPe();

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

    }


    public void cycle(CBRQuery cbrQuery, int similarity)  {

        try {
            //:: :: RETRIEVE :: ::
            NNConfig simConfig = null;

            switch (similarity) {
                case TrucoData.QUERY_ENVIDO_MAO:
                    simConfig = similarityEnvidoMao.getSimilarityConfig();
                    break;
                case TrucoData.QUERY_TRUCO_MAO_1:
                    simConfig = similarityTrucoMao1.getSimilarityConfig();
                    break;
                case TrucoData.QUERY_ENVIDO_PE:
                    simConfig = similarityEnvidoPe.getSimilarityConfig();
                    break;
                case TrucoData.QUERY_TRUCO_PE:
                    simConfig = similarityTrucoPe.getSimilarityConfig();
                    break;
            }

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
                }
            }
            if (cases.size() > 0) {
                JSONParser parser = new JSONParser();
                try {
                    jsonCaseRetrieved = (JSONObject) parser.parse(cases.get(0).get_case().getDescription().toString());
                    System.out.println(jsonCaseRetrieved);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else
                jsonCaseRetrieved = null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
            e.printStackTrace();
        }

    }

    @Override
    public void postCycle() throws ExecutionException {

    }

    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                logger.info("Entrou no método run");
                try{
                    boolean nimbusFound = false;
                    for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                        if(info.getName().equals("Nimbus")){
                            UIManager.setLookAndFeel(info.getClassName());
                            nimbusFound = true;
                            break;
                        }
                    }
                    if(!nimbusFound){
                        int option = JOptionPane.showConfirmDialog(null,
                                "Nimbus Look And Feel not found\n"+
                                        "Do you want to proceed?",
                                "Warning",JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if(option == JOptionPane.NO_OPTION){
                            System.exit(0);
                        }
                    }
                    TrucoCBR mainFrame = getInstance();
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setResizable(false);
                    mainFrame.setVisible(true);

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getStackTrace());
                    e.printStackTrace();
                }
            }
        });
    }


}
