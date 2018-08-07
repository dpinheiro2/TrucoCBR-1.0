package br.ufsm.trucocbr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Player {

    private Baralho cartas;
    private Baralho cartasJogadas;
    private int pontos;
    private int hierarquiaCartaAlta;
    private int hierarquiaCartaMedia;
    private int hierarquiaCartaBaixa;

    public Player(Baralho cartas) {
        this.cartas = cartas;
        this.pontos = 0;
        this.cartasJogadas = new Baralho();
    }

    public Baralho getCartas() {
        return cartas;
    }

    public void setCartas(Baralho cartas) {
        this.cartas = cartas;
    }

    public Baralho getCartasJogadas() {
        return cartasJogadas;
    }

    public void setCartasJogadas(Baralho cartasJogadas) {
        this.cartasJogadas = cartasJogadas;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getHierarquiaCartaAlta() {
        return hierarquiaCartaAlta;
    }

    public void setHierarquiaCartaAlta(int hierarquiaCartaAlta) {
        this.hierarquiaCartaAlta = hierarquiaCartaAlta;
    }

    public int getHierarquiaCartaMedia() {
        return hierarquiaCartaMedia;
    }

    public void setHierarquiaCartaMedia(int hierarquiaCartaMedia) {
        this.hierarquiaCartaMedia = hierarquiaCartaMedia;
    }

    public int getHierarquiaCartaBaixa() {
        return hierarquiaCartaBaixa;
    }

    public void setHierarquiaCartaBaixa(int hierarquiaCartaBaixa) {
        this.hierarquiaCartaBaixa = hierarquiaCartaBaixa;
    }

    public void entregarCartas() {
        this.cartas.devolverCartasMonte();
        this.cartasJogadas.devolverCartasMonte();
    }


    public int getEnvido(){

        if (this.getFlor() > 0) {
            return this.getFlor();
        } else {
            int ptsEnvido = 0;
            int maiorPonto = 0;
            ArrayList<Carta> espadas = new ArrayList<Carta>();
            ArrayList<Carta> paus = new ArrayList<Carta>();
            ArrayList<Carta> ouro = new ArrayList<Carta>();
            ArrayList<Carta> copas = new ArrayList<Carta>();

            Baralho todasCartas = new Baralho();
            for(Carta carta: this.cartas.getCartas())
            todasCartas.addCarta(carta);

            for(Carta carta: this.cartasJogadas.getCartas())
                todasCartas.addCarta(carta);

            for(Carta carta: todasCartas.getCartas()) {
                if (carta.getPontosCarta(carta.getNomeFace()) > maiorPonto)
                    maiorPonto = carta.getPontosCarta(carta.getNomeFace());
                switch (carta.getNaipe()) {
                    case Espadas:
                        espadas.add(carta);
                        break;
                    case Ouro:
                        ouro.add(carta);
                        break;
                    case Paus:
                        paus.add(carta);
                        break;
                    case Copas:
                        copas.add(carta);
                        break;
                }
            }

            if (ouro.size() == 2) {
                ptsEnvido += 20;
                for (Carta carta : ouro){
                    ptsEnvido += carta.getPontosCarta(carta.getNomeFace());
                }

            } else if (espadas.size() == 2) {
                ptsEnvido += 20;
                for (Carta carta : espadas){
                    ptsEnvido += carta.getPontosCarta(carta.getNomeFace());
                }

            } else if(paus.size() == 2) {
                ptsEnvido += 20;
                for (Carta carta : paus){
                    ptsEnvido += carta.getPontosCarta(carta.getNomeFace());
                }

            } else if (copas.size() == 2) {
                ptsEnvido += 20;
                for (Carta carta : copas){
                    ptsEnvido += carta.getPontosCarta(carta.getNomeFace());
                }
            } else
                ptsEnvido = maiorPonto;

            return ptsEnvido;
        }
    }



    public int getFlor(){

        int ptsFlor = 0;
        ArrayList<Carta> espadas = new ArrayList<Carta>();
        ArrayList<Carta> paus = new ArrayList<Carta>();
        ArrayList<Carta> ouro = new ArrayList<Carta>();
        ArrayList<Carta> copas = new ArrayList<Carta>();

        Baralho todasCartas = new Baralho();
        for(Carta carta: this.cartas.getCartas())
            todasCartas.addCarta(carta);

        for(Carta carta: this.cartasJogadas.getCartas())
            todasCartas.addCarta(carta);

        for(Carta carta: this.cartas.getCartas()) {

            switch (carta.getNaipe()) {
                case Espadas:
                    espadas.add(carta);
                    break;
                case Ouro:
                    ouro.add(carta);
                    break;
                case Paus:
                    paus.add(carta);
                    break;
                case Copas:
                    copas.add(carta);
                    break;
            }
        }

        if (ouro.size() == 3) {
            ptsFlor += 20;
            for (Carta carta : ouro){
                ptsFlor += carta.getPontosCarta(carta.getNomeFace());
            }

        } else if (espadas.size() == 3) {
            ptsFlor += 20;
            for (Carta carta : espadas){
                ptsFlor += carta.getPontosCarta(carta.getNomeFace());
            }

        } else if(paus.size() == 3) {
            ptsFlor += 20;
            for (Carta carta : paus){
                ptsFlor += carta.getPontosCarta(carta.getNomeFace());
            }

        } else if (copas.size() == 3) {
            ptsFlor += 20;
            for (Carta carta : copas){
                ptsFlor += carta.getPontosCarta(carta.getNomeFace());
            }
        }
        return ptsFlor;

    }

    public Carta getCartaByHierarquia(int hierarquia) {
        Carta carta = null;

        for (Carta card : cartas.getCartas()) {
            if (card.getHierarquia() == hierarquia)
                carta = card;
        }

        return carta;
    }

    public Carta getCartaParaVencerRodada(int hierarquiaCartaOponente) {
        Carta carta = null;
        List<Integer> hierarquias = getOrderCartas();
        for (int i=0; i < hierarquias.size(); i++){
            if (hierarquias.get(i) <= hierarquiaCartaOponente)
                carta = getCartaByHierarquia(hierarquias.get(i));
        }

        return carta;
    }

    public Carta getMaiorCarta(Baralho cartas) {

        List<Integer> hierarquias = new ArrayList();
        Carta maiorCarta = null;

        for (Carta carta : cartas.getCartas())
            hierarquias.add(carta.getHierarquia());

        Collections.sort(hierarquias);

        for (Carta carta : cartas.getCartas())
            if (hierarquias.get(0) == carta.getHierarquia())
                maiorCarta = carta;

        return maiorCarta;

    }

    public Carta getMenorCarta(Baralho cartas) {

        List<Integer> hierarquias = new ArrayList();
        Carta menorCarta = null;

        for (Carta carta : cartas.getCartas())
            hierarquias.add(carta.getHierarquia());

        Collections.sort(hierarquias);

        for (Carta carta : cartas.getCartas())
            if (hierarquias.get(hierarquias.size()-1) == carta.getHierarquia())
                menorCarta = carta;

        return menorCarta;

    }

    public List<Integer> getOrderCartas() {
        List<Integer> hierarquias = new ArrayList();
        for(Carta carta: this.cartas.getCartas())
            hierarquias.add(carta.getHierarquia());
        Collections.sort(hierarquias);
        return hierarquias;
    }

    public void setOrderCartas() {

        Baralho todasCartas = new Baralho();
        for(Carta carta: this.cartas.getCartas())
            todasCartas.addCarta(carta);

        for(Carta carta: this.cartasJogadas.getCartas())
            todasCartas.addCarta(carta);

        List<Integer> hierarquias = new ArrayList();

        for (Carta carta : todasCartas.getCartas())
            hierarquias.add(carta.getHierarquia());

        Collections.sort(hierarquias);
        this.hierarquiaCartaAlta = hierarquias.get(0);
        this.hierarquiaCartaMedia = hierarquias.get(1);
        this.hierarquiaCartaBaixa = hierarquias.get(2);
    }

    public Carta jogarCarta(Carta carta) {
        if (cartas.getCartas().contains(carta)) {
            cartas.getCartas().remove(carta);
            cartasJogadas.getCartas().add(carta);
        }
        return carta;
    }

    public void somarPontos(int qtdePts) {
        pontos += qtdePts;
    }

    public void resetPontos() {
        pontos = 0;
    }
}
