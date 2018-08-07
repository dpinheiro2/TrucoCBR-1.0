package br.ufsm.trucocbr.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class TrucoDescription implements CaseComponent {

    String idMao;
    String indJogMao;
    String cartaAltaComp;
    String cartaMediaComp;
    String cartaBaixaComp;
    String cartaAltaHumano;
    String cartaMediaHumano;
    String cartaBaixaHumano;
    String carta1Comp;
    String carta1Humano;
    String carta2Comp;
    String carta2Humano;
    String carta3Comp;
    String carta3Humano;
    String ganhadorRodada1;
    String ganhadorRodada2;
    String ganhadorRodada3;
    Integer cartaViradaComp;
    Integer cartaViradaHumano;
    String pediuEnvido;
    String pediuFaltaEnvido;
    String pediuRealEnvido;
    Integer ptEnvidoComp;
    Integer ptEnvidoHumano;
    String jogNaoEnvido;
    String jogGanhouEnvido;
    Integer ptGanhosEnvido;
    String cantouFlor;
    String cantouContraFlor;
    String cantouContraFlorResto;
    String jogNaoQuisFlor;
    Integer ptFlorComp;
    Integer ptFlorHumano;
    String ganhadorFlor;
    Integer ptGanhosFlor;
    String naoMostrouEnvido;
    String naoMostrouFlor;
    String jogPediuTruco;
    Integer rodadaPediuTruco;
    String jogPediuRetruco;
    Integer rodadaPediuRetruco;
    String jogPediuVale4;
    Integer rodadaPediuVale4;
    String jogNaoTruco;
    String jogGanhouTruco;
    Integer ptsGanhosTruco;
    Integer ptCompAntMao;
    Integer ptHumanoAntMao;
    Integer ptCompPosMao;
    Integer ptHumanoPosMao;
    boolean compMentiuEnvido;
    boolean humanoMentiuEnvido;
    boolean compMentiuFlor;
    boolean humanoMentiuFlor;
    boolean compMentiuTruco;
    boolean humanoMentiuTruco;
    String jogFoiBaralho;
    Integer ptsGanhadorEnvido;
    boolean ganhadorEnvidoMao;
    Integer ptsjogoVencedorEnvido;
    Integer ptsjogoPerdedorEnvido;
    boolean ganhadorPediuEnvido;
    boolean ganhadorPediuReal;
    boolean ganhadorPediuFalta;
    Integer diffPontos;
    boolean ganhadorTrucoMao;
    boolean ganhadorPediuTruco;
    boolean ganhadorPediuRetruco;
    boolean ganhadorPediuVale4;
    Integer hierarquiaAVencMao;
    Integer hierarquiaMVencMao;
    Integer hierarquiaBVencMao;
    boolean vencedorMaoIsMao;
    boolean vencedorRod1Mao;
    boolean vencedorRod2Mao;
    boolean vencedorRod3Mao;
    String vencedorMao;
    Integer hierCart1VencMao;
    Integer hierCart2VencMao;
    Integer hierCart3VencMao;
    Integer hierCart1PerdeMao;
    Integer hierCart2PerdeMao;
    Integer hierCart3PerdeMao;
    boolean perdedorNaoEnvido;
    boolean perdedorNaoTruco;

    public String getIdMao() {
        return idMao;
    }

    public void setIdMao(String idMao) {
        this.idMao = idMao;
    }

    public String getIndJogMao() {
        return indJogMao;
    }

    public void setIndJogMao(String indJogMao) {
        this.indJogMao = indJogMao;
    }

    public String getCartaAltaComp() {
        return cartaAltaComp;
    }

    public void setCartaAltaComp(String cartaAltaComp) {
        this.cartaAltaComp = cartaAltaComp;
    }

    public String getCartaMediaComp() {
        return cartaMediaComp;
    }

    public void setCartaMediaComp(String cartaMediaComp) {
        this.cartaMediaComp = cartaMediaComp;
    }

    public String getCartaBaixaComp() {
        return cartaBaixaComp;
    }

    public void setCartaBaixaComp(String cartaBaixaComp) {
        this.cartaBaixaComp = cartaBaixaComp;
    }

    public String getCartaAltaHumano() {
        return cartaAltaHumano;
    }

    public void setCartaAltaHumano(String cartaAltaHumano) {
        this.cartaAltaHumano = cartaAltaHumano;
    }

    public String getCartaMediaHumano() {
        return cartaMediaHumano;
    }

    public void setCartaMediaHumano(String cartaMediaHumano) {
        this.cartaMediaHumano = cartaMediaHumano;
    }

    public String getCartaBaixaHumano() {
        return cartaBaixaHumano;
    }

    public void setCartaBaixaHumano(String cartaBaixaHumano) {
        this.cartaBaixaHumano = cartaBaixaHumano;
    }

    public String getCarta1Comp() {
        return carta1Comp;
    }

    public void setCarta1Comp(String carta1Comp) {
        this.carta1Comp = carta1Comp;
    }

    public String getCarta1Humano() {
        return carta1Humano;
    }

    public void setCarta1Humano(String carta1Humano) {
        this.carta1Humano = carta1Humano;
    }

    public String getCarta2Comp() {
        return carta2Comp;
    }

    public void setCarta2Comp(String carta2Comp) {
        this.carta2Comp = carta2Comp;
    }

    public String getCarta2Humano() {
        return carta2Humano;
    }

    public void setCarta2Humano(String carta2Humano) {
        this.carta2Humano = carta2Humano;
    }

    public String getCarta3Comp() {
        return carta3Comp;
    }

    public void setCarta3Comp(String carta3Comp) {
        this.carta3Comp = carta3Comp;
    }

    public String getCarta3Humano() {
        return carta3Humano;
    }

    public void setCarta3Humano(String carta3Humano) {
        this.carta3Humano = carta3Humano;
    }

    public String getGanhadorRodada1() {
        return ganhadorRodada1;
    }

    public void setGanhadorRodada1(String ganhadorRodada1) {
        this.ganhadorRodada1 = ganhadorRodada1;
    }

    public String getGanhadorRodada2() {
        return ganhadorRodada2;
    }

    public void setGanhadorRodada2(String ganhadorRodada2) {
        this.ganhadorRodada2 = ganhadorRodada2;
    }

    public String getGanhadorRodada3() {
        return ganhadorRodada3;
    }

    public void setGanhadorRodada3(String ganhadorRodada3) {
        this.ganhadorRodada3 = ganhadorRodada3;
    }

    public Integer getCartaViradaComp() {
        return cartaViradaComp;
    }

    public void setCartaViradaComp(Integer cartaViradaComp) {
        this.cartaViradaComp = cartaViradaComp;
    }

    public Integer getCartaViradaHumano() {
        return cartaViradaHumano;
    }

    public void setCartaViradaHumano(Integer cartaViradaHumano) {
        this.cartaViradaHumano = cartaViradaHumano;
    }

    public String getPediuEnvido() {
        return pediuEnvido;
    }

    public void setPediuEnvido(String pediuEnvido) {
        this.pediuEnvido = pediuEnvido;
    }

    public String getPediuFaltaEnvido() {
        return pediuFaltaEnvido;
    }

    public void setPediuFaltaEnvido(String pediuFaltaEnvido) {
        this.pediuFaltaEnvido = pediuFaltaEnvido;
    }

    public String getPediuRealEnvido() {
        return pediuRealEnvido;
    }

    public void setPediuRealEnvido(String pediuRealEnvido) {
        this.pediuRealEnvido = pediuRealEnvido;
    }

    public Integer getPtEnvidoComp() {
        return ptEnvidoComp;
    }

    public void setPtEnvidoComp(Integer ptEnvidoComp) {
        this.ptEnvidoComp = ptEnvidoComp;
    }

    public Integer getPtEnvidoHumano() {
        return ptEnvidoHumano;
    }

    public void setPtEnvidoHumano(Integer ptEnvidoHumano) {
        this.ptEnvidoHumano = ptEnvidoHumano;
    }

    public String getJogNaoEnvido() {
        return jogNaoEnvido;
    }

    public void setJogNaoEnvido(String jogNaoEnvido) {
        this.jogNaoEnvido = jogNaoEnvido;
    }

    public String getJogGanhouEnvido() {
        return jogGanhouEnvido;
    }

    public void setJogGanhouEnvido(String jogGanhouEnvido) {
        this.jogGanhouEnvido = jogGanhouEnvido;
    }

    public Integer getPtGanhosEnvido() {
        return ptGanhosEnvido;
    }

    public void setPtGanhosEnvido(Integer ptGanhosEnvido) {
        this.ptGanhosEnvido = ptGanhosEnvido;
    }

    public String getCantouFlor() {
        return cantouFlor;
    }

    public void setCantouFlor(String cantouFlor) {
        this.cantouFlor = cantouFlor;
    }

    public String getCantouContraFlor() {
        return cantouContraFlor;
    }

    public void setCantouContraFlor(String cantouContraFlor) {
        this.cantouContraFlor = cantouContraFlor;
    }

    public String getCantouContraFlorResto() {
        return cantouContraFlorResto;
    }

    public void setCantouContraFlorResto(String cantouContraFlorResto) {
        this.cantouContraFlorResto = cantouContraFlorResto;
    }

    public String getJogNaoQuisFlor() {
        return jogNaoQuisFlor;
    }

    public void setJogNaoQuisFlor(String jogNaoQuisFlor) {
        this.jogNaoQuisFlor = jogNaoQuisFlor;
    }

    public Integer getPtFlorComp() {
        return ptFlorComp;
    }

    public void setPtFlorComp(Integer ptFlorComp) {
        this.ptFlorComp = ptFlorComp;
    }

    public Integer getPtFlorHumano() {
        return ptFlorHumano;
    }

    public void setPtFlorHumano(Integer ptFlorHumano) {
        this.ptFlorHumano = ptFlorHumano;
    }

    public String getGanhadorFlor() {
        return ganhadorFlor;
    }

    public void setGanhadorFlor(String ganhadorFlor) {
        this.ganhadorFlor = ganhadorFlor;
    }

    public Integer getPtGanhosFlor() {
        return ptGanhosFlor;
    }

    public void setPtGanhosFlor(Integer ptGanhosFlor) {
        this.ptGanhosFlor = ptGanhosFlor;
    }

    public String getNaoMostrouEnvido() {
        return naoMostrouEnvido;
    }

    public void setNaoMostrouEnvido(String naoMostrouEnvido) {
        this.naoMostrouEnvido = naoMostrouEnvido;
    }

    public String getNaoMostrouFlor() {
        return naoMostrouFlor;
    }

    public void setNaoMostrouFlor(String naoMostrouFlor) {
        this.naoMostrouFlor = naoMostrouFlor;
    }

    public String getJogPediuTruco() {
        return jogPediuTruco;
    }

    public void setJogPediuTruco(String jogPediuTruco) {
        this.jogPediuTruco = jogPediuTruco;
    }

    public Integer getRodadaPediuTruco() {
        return rodadaPediuTruco;
    }

    public void setRodadaPediuTruco(Integer rodadaPediuTruco) {
        this.rodadaPediuTruco = rodadaPediuTruco;
    }

    public String getJogPediuRetruco() {
        return jogPediuRetruco;
    }

    public void setJogPediuRetruco(String jogPediuRetruco) {
        this.jogPediuRetruco = jogPediuRetruco;
    }

    public Integer getRodadaPediuRetruco() {
        return rodadaPediuRetruco;
    }

    public void setRodadaPediuRetruco(Integer rodadaPediuRetruco) {
        this.rodadaPediuRetruco = rodadaPediuRetruco;
    }

    public String getJogPediuVale4() {
        return jogPediuVale4;
    }

    public void setJogPediuVale4(String jogPediuVale4) {
        this.jogPediuVale4 = jogPediuVale4;
    }

    public Integer getRodadaPediuVale4() {
        return rodadaPediuVale4;
    }

    public void setRodadaPediuVale4(Integer rodadaPediuVale4) {
        this.rodadaPediuVale4 = rodadaPediuVale4;
    }

    public String getJogNaoTruco() {
        return jogNaoTruco;
    }

    public void setJogNaoTruco(String jogNaoTruco) {
        this.jogNaoTruco = jogNaoTruco;
    }

    public String getJogGanhouTruco() {
        return jogGanhouTruco;
    }

    public void setJogGanhouTruco(String jogGanhouTruco) {
        this.jogGanhouTruco = jogGanhouTruco;
    }

    public Integer getPtsGanhosTruco() {
        return ptsGanhosTruco;
    }

    public void setPtsGanhosTruco(Integer ptsGanhosTruco) {
        this.ptsGanhosTruco = ptsGanhosTruco;
    }

    public Integer getPtCompAntMao() {
        return ptCompAntMao;
    }

    public void setPtCompAntMao(Integer ptCompAntMao) {
        this.ptCompAntMao = ptCompAntMao;
    }

    public Integer getPtHumanoAntMao() {
        return ptHumanoAntMao;
    }

    public void setPtHumanoAntMao(Integer ptHumanoAntMao) {
        this.ptHumanoAntMao = ptHumanoAntMao;
    }

    public Integer getPtCompPosMao() {
        return ptCompPosMao;
    }

    public void setPtCompPosMao(Integer ptCompPosMao) {
        this.ptCompPosMao = ptCompPosMao;
    }

    public Integer getPtHumanoPosMao() {
        return ptHumanoPosMao;
    }

    public void setPtHumanoPosMao(Integer ptHumanoPosMao) {
        this.ptHumanoPosMao = ptHumanoPosMao;
    }

    public boolean isCompMentiuEnvido() {
        return compMentiuEnvido;
    }

    public void setCompMentiuEnvido(boolean compMentiuEnvido) {
        this.compMentiuEnvido = compMentiuEnvido;
    }

    public boolean isHumanoMentiuEnvido() {
        return humanoMentiuEnvido;
    }

    public void setHumanoMentiuEnvido(boolean humanoMentiuEnvido) {
        this.humanoMentiuEnvido = humanoMentiuEnvido;
    }

    public boolean isCompMentiuFlor() {
        return compMentiuFlor;
    }

    public void setCompMentiuFlor(boolean compMentiuFlor) {
        this.compMentiuFlor = compMentiuFlor;
    }

    public boolean isHumanoMentiuFlor() {
        return humanoMentiuFlor;
    }

    public void setHumanoMentiuFlor(boolean humanoMentiuFlor) {
        this.humanoMentiuFlor = humanoMentiuFlor;
    }

    public boolean isCompMentiuTruco() {
        return compMentiuTruco;
    }

    public void setCompMentiuTruco(boolean compMentiuTruco) {
        this.compMentiuTruco = compMentiuTruco;
    }

    public boolean isHumanoMentiuTruco() {
        return humanoMentiuTruco;
    }

    public void setHumanoMentiuTruco(boolean humanoMentiuTruco) {
        this.humanoMentiuTruco = humanoMentiuTruco;
    }

    public String getJogFoiBaralho() {
        return jogFoiBaralho;
    }

    public void setJogFoiBaralho(String jogFoiBaralho) {
        this.jogFoiBaralho = jogFoiBaralho;
    }

    public Integer getPtsGanhadorEnvido() {
        return ptsGanhadorEnvido;
    }

    public void setPtsGanhadorEnvido(Integer ptsGanhadorEnvido) {
        this.ptsGanhadorEnvido = ptsGanhadorEnvido;
    }

    public boolean isGanhadorEnvidoMao() {
        return ganhadorEnvidoMao;
    }

    public void setGanhadorEnvidoMao(boolean ganhadorEnvidoMao) {
        this.ganhadorEnvidoMao = ganhadorEnvidoMao;
    }

    public Integer getPtsjogoVencedorEnvido() {
        return ptsjogoVencedorEnvido;
    }

    public void setPtsjogoVencedorEnvido(Integer ptsjogoVencedorEnvido) {
        this.ptsjogoVencedorEnvido = ptsjogoVencedorEnvido;
    }

    public Integer getPtsjogoPerdedorEnvido() {
        return ptsjogoPerdedorEnvido;
    }

    public void setPtsjogoPerdedorEnvido(Integer ptsjogoPerdedorEnvido) {
        this.ptsjogoPerdedorEnvido = ptsjogoPerdedorEnvido;
    }

    public boolean isGanhadorPediuEnvido() {
        return ganhadorPediuEnvido;
    }

    public void setGanhadorPediuEnvido(boolean ganhadorPediuEnvido) {
        this.ganhadorPediuEnvido = ganhadorPediuEnvido;
    }

    public boolean isGanhadorPediuReal() {
        return ganhadorPediuReal;
    }

    public void setGanhadorPediuReal(boolean ganhadorPediuReal) {
        this.ganhadorPediuReal = ganhadorPediuReal;
    }

    public boolean isGanhadorPediuFalta() {
        return ganhadorPediuFalta;
    }

    public void setGanhadorPediuFalta(boolean ganhadorPediuFalta) {
        this.ganhadorPediuFalta = ganhadorPediuFalta;
    }

    public Integer getDiffPontos() {
        return diffPontos;
    }

    public void setDiffPontos(Integer diffPontos) {
        this.diffPontos = diffPontos;
    }

    public boolean isGanhadorTrucoMao() {
        return ganhadorTrucoMao;
    }

    public void setGanhadorTrucoMao(boolean ganhadorTrucoMao) {
        this.ganhadorTrucoMao = ganhadorTrucoMao;
    }

    public boolean isGanhadorPediuTruco() {
        return ganhadorPediuTruco;
    }

    public void setGanhadorPediuTruco(boolean ganhadorPediuTruco) {
        this.ganhadorPediuTruco = ganhadorPediuTruco;
    }

    public boolean isGanhadorPediuRetruco() {
        return ganhadorPediuRetruco;
    }

    public void setGanhadorPediuRetruco(boolean ganhadorPediuRetruco) {
        this.ganhadorPediuRetruco = ganhadorPediuRetruco;
    }

    public boolean isGanhadorPediuVale4() {
        return ganhadorPediuVale4;
    }

    public void setGanhadorPediuVale4(boolean ganhadorPediuVale4) {
        this.ganhadorPediuVale4 = ganhadorPediuVale4;
    }

    public Integer getHierarquiaAVencMao() {
        return hierarquiaAVencMao;
    }

    public void setHierarquiaAVencMao(Integer hierarquiaAVencMao) {
        this.hierarquiaAVencMao = hierarquiaAVencMao;
    }

    public Integer getHierarquiaMVencMao() {
        return hierarquiaMVencMao;
    }

    public void setHierarquiaMVencMao(Integer hierarquiaMVencMao) {
        this.hierarquiaMVencMao = hierarquiaMVencMao;
    }

    public Integer getHierarquiaBVencMao() {
        return hierarquiaBVencMao;
    }

    public void setHierarquiaBVencMao(Integer hierarquiaBVencMao) {
        this.hierarquiaBVencMao = hierarquiaBVencMao;
    }

    public boolean isVencedorMaoIsMao() {
        return vencedorMaoIsMao;
    }

    public void setVencedorMaoIsMao(boolean vencedorMaoIsMao) {
        this.vencedorMaoIsMao = vencedorMaoIsMao;
    }

    public boolean isVencedorRod1Mao() {
        return vencedorRod1Mao;
    }

    public void setVencedorRod1Mao(boolean vencedorRod1Mao) {
        this.vencedorRod1Mao = vencedorRod1Mao;
    }

    public boolean isVencedorRod2Mao() {
        return vencedorRod2Mao;
    }

    public void setVencedorRod2Mao(boolean vencedorRod2Mao) {
        this.vencedorRod2Mao = vencedorRod2Mao;
    }

    public boolean isVencedorRod3Mao() {
        return vencedorRod3Mao;
    }

    public void setVencedorRod3Mao(boolean vencedorRod3Mao) {
        this.vencedorRod3Mao = vencedorRod3Mao;
    }

    public String getVencedorMao() {
        return vencedorMao;
    }

    public void setVencedorMao(String vencedorMao) {
        this.vencedorMao = vencedorMao;
    }

    public Integer getHierCart1VencMao() {
        return hierCart1VencMao;
    }

    public void setHierCart1VencMao(Integer hierCart1VencMao) {
        this.hierCart1VencMao = hierCart1VencMao;
    }

    public Integer getHierCart2VencMao() {
        return hierCart2VencMao;
    }

    public void setHierCart2VencMao(Integer hierCart2VencMao) {
        this.hierCart2VencMao = hierCart2VencMao;
    }

    public Integer getHierCart3VencMao() {
        return hierCart3VencMao;
    }

    public void setHierCart3VencMao(Integer hierCart3VencMao) {
        this.hierCart3VencMao = hierCart3VencMao;
    }

    public Integer getHierCart1PerdeMao() {
        return hierCart1PerdeMao;
    }

    public void setHierCart1PerdeMao(Integer hierCart1PerdeMao) {
        this.hierCart1PerdeMao = hierCart1PerdeMao;
    }

    public Integer getHierCart2PerdeMao() {
        return hierCart2PerdeMao;
    }

    public void setHierCart2PerdeMao(Integer hierCart2PerdeMao) {
        this.hierCart2PerdeMao = hierCart2PerdeMao;
    }

    public Integer getHierCart3PerdeMao() {
        return hierCart3PerdeMao;
    }

    public void setHierCart3PerdeMao(Integer hierCart3PerdeMao) {
        this.hierCart3PerdeMao = hierCart3PerdeMao;
    }

    public boolean isPerdedorNaoEnvido() {
        return perdedorNaoEnvido;
    }

    public void setPerdedorNaoEnvido(boolean perdedorNaoEnvido) {
        this.perdedorNaoEnvido = perdedorNaoEnvido;
    }

    public boolean isPerdedorNaoTruco() {
        return perdedorNaoTruco;
    }

    public void setPerdedorNaoTruco(boolean perdedorNaoTruco) {
        this.perdedorNaoTruco = perdedorNaoTruco;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idMao\":\"" + idMao + "\"" +
                ",\"indJogMao\":\"" + indJogMao + "\"" +
                ",\"cartaAltaComp\":\"" + cartaAltaComp + "\"" +
                ",\"cartaMediaComp\":\"" + cartaMediaComp + "\"" +
                ",\"cartaBaixaComp\":\"" + cartaBaixaComp + "\"" +
                ",\"cartaAltaHumano\":\"" + cartaAltaHumano + "\"" +
                ",\"cartaMediaHumano\":\"" + cartaMediaHumano + "\"" +
                ",\"cartaBaixaHumano\":\"" + cartaBaixaHumano + "\"" +
                ",\"carta1Comp\":\"" + carta1Comp + "\"" +
                ",\"carta1Humano\":\"" + carta1Humano + "\"" +
                ",\"carta2Comp\":\"" + carta2Comp + "\"" +
                ",\"carta2Humano\":\"" + carta2Humano + "\"" +
                ",\"carta3Comp\":\"" + carta3Comp + "\"" +
                ",\"carta3Humano\":\"" + carta3Humano + "\"" +
                ",\"ganhadorRodada1\":\"" + ganhadorRodada1 + "\"" +
                ",\"ganhadorRodada2\":\"" + ganhadorRodada2 + "\"" +
                ",\"ganhadorRodada3\":\"" + ganhadorRodada3 + "\"" +
                ",\"cartaViradaComp\":" + cartaViradaComp +
                ",\"cartaViradaHumano\":" + cartaViradaHumano +
                ",\"pediuEnvido\":\"" + pediuEnvido + "\"" +
                ",\"pediuFaltaEnvido\":\"" + pediuFaltaEnvido + "\"" +
                ",\"pediuRealEnvido\":\"" + pediuRealEnvido + "\"" +
                ",\"ptEnvidoComp\":" + ptEnvidoComp +
                ",\"ptEnvidoHumano\":" + ptEnvidoHumano +
                ",\"jogNaoEnvido\":\"" + jogNaoEnvido + "\"" +
                ",\"jogGanhouEnvido\":\"" + jogGanhouEnvido + "\"" +
                ",\"ptGanhosEnvido\":" + ptGanhosEnvido +
                ",\"cantouFlor\":\"" + cantouFlor + "\"" +
                ",\"cantouContraFlor\":\"" + cantouContraFlor + "\"" +
                ",\"cantouContraFlorResto\":\"" + cantouContraFlorResto + "\"" +
                ",\"jogNaoQuisFlor\":\"" + jogNaoQuisFlor + "\"" +
                ",\"ptFlorComp\":" + ptFlorComp +
                ",\"ptFlorHumano\":" + ptFlorHumano +
                ",\"ganhadorFlor\":\"" + ganhadorFlor + "\"" +
                ",\"ptGanhosFlor\":" + ptGanhosFlor +
                ",\"naoMostrouEnvido\":\"" + naoMostrouEnvido + "\"" +
                ",\"naoMostrouFlor\":\"" + naoMostrouFlor + "\"" +
                ",\"jogPediuTruco\":\"" + jogPediuTruco + "\"" +
                ",\"rodadaPediuTruco\":" + rodadaPediuTruco +
                ",\"jogPediuRetruco\":\"" + jogPediuRetruco + "\"" +
                ",\"rodadaPediuRetruco\":" + rodadaPediuRetruco +
                ",\"jogPediuVale4\":\"" + jogPediuVale4 + "\"" +
                ",\"rodadaPediuVale4\":" + rodadaPediuVale4 +
                ",\"jogNaoTruco\":\"" + jogNaoTruco + "\"" +
                ",\"jogGanhouTruco\":\"" + jogGanhouTruco + "\"" +
                ",\"ptsGanhosTruco\":" + ptsGanhosTruco +
                ",\"ptCompAntMao\":" + ptCompAntMao +
                ",\"ptHumanoAntMao\":" + ptHumanoAntMao +
                ",\"ptCompPosMao\":" + ptCompPosMao +
                ",\"ptHumanoPosMao\":" + ptHumanoPosMao +
                ",\"compMentiuEnvido\":" + compMentiuEnvido +
                ",\"humanoMentiuEnvido\":" + humanoMentiuEnvido +
                ",\"compMentiuFlor\":" + compMentiuFlor +
                ",\"humanoMentiuFlor\":" + humanoMentiuFlor +
                ",\"compMentiuTruco\":" + compMentiuTruco +
                ",\"humanoMentiuTruco\":" + humanoMentiuTruco +
                ",\"jogFoiBaralho\":\"" + jogFoiBaralho + "\"" +
                ",\"ptsGanhadorEnvido\":" + ptsGanhadorEnvido +
                ",\"ganhadorEnvidoMao\":" + ganhadorEnvidoMao +
                ",\"ptsjogoVencedorEnvido\":" + ptsjogoVencedorEnvido +
                ",\"ptsjogoPerdedorEnvido\":" + ptsjogoPerdedorEnvido +
                ",\"ganhadorPediuEnvido\":" + ganhadorPediuEnvido +
                ",\"ganhadorPediuReal\":" + ganhadorPediuReal +
                ",\"ganhadorPediuFalta\":" + ganhadorPediuFalta +
                ",\"diffPontos\":" + diffPontos +
                ",\"ganhadorTrucoMao\":" + ganhadorTrucoMao +
                ",\"ganhadorPediuTruco\":" + ganhadorPediuTruco +
                ",\"ganhadorPediuRetruco\":" + ganhadorPediuRetruco +
                ",\"ganhadorPediuVale4\":" + ganhadorPediuVale4 +
                ",\"hierarquiaAVencMao\":" + hierarquiaAVencMao +
                ",\"hierarquiaMVencMao\":" + hierarquiaMVencMao +
                ",\"hierarquiaBVencMao\":" + hierarquiaBVencMao +
                ",\"vencedorMaoIsMao\":" + vencedorMaoIsMao +
                ",\"vencedorRod1Mao\":" + vencedorRod1Mao +
                ",\"vencedorRod2Mao\":" + vencedorRod2Mao +
                ",\"vencedorRod3Mao\":" + vencedorRod3Mao +
                ",\"vencedorMao\":\"" + vencedorMao + "\"" +
                ",\"hierCart1VencMao\":" + hierCart1VencMao +
                ",\"hierCart2VencMao\":" + hierCart2VencMao +
                ",\"hierCart3VencMao\":" + hierCart3VencMao +
                ",\"hierCart1PerdeMao\":" + hierCart1PerdeMao +
                ",\"hierCart2PerdeMao\":" + hierCart2PerdeMao +
                ",\"hierCart3PerdeMao\":" + hierCart3PerdeMao +
                ",\"perdedorNaoEnvido\":" + perdedorNaoEnvido +
                ",\"perdedorNaoTruco\":" + perdedorNaoTruco +
                '}';

    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("idMao", this.getClass());
    }
}
