package br.ufsm.trucocbr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Baralho {
    private ArrayList<Carta> cartas;


    // Extrair cartas individualmente a partir de uma única imagem
    static final int largura = 208;
    static final int altura = 319;

    public Baralho() {

        //Create a new deck of playing cards
        this.cartas = new ArrayList<Carta>();


    }

    //Add 40 playing cards to a deck
    public void criarBaralho(){

        try {
            URL resource = getClass().getResource("/br/ufsm/trucocbr/res/img/allCards.png");
            File file = new File(resource.toURI());
            BufferedImage imgCompleta = ImageIO.read(file);
            BufferedImage imgCartaTemp;

        //Generate Cards
        //Loop Through Suits
        for(Naipe naipe : Naipe.values()){
            //Loop through Values
            for(Face face : Face.values()){

                if (face.ordinal() > 6) {
                    imgCartaTemp = imgCompleta.getSubimage(
                            (face.ordinal() + 2)*largura,
                            naipe.ordinal()*altura,
                            largura,
                            altura);

                } else {
                    imgCartaTemp = imgCompleta.getSubimage(
                            face.ordinal()*largura,
                            naipe.ordinal()*altura,
                            largura,
                            altura);
                }

                //Image resizedImage = imgCartaTemp.getScaledInstance(115,174, Image.SCALE_SMOOTH);
                //Image resizedImage = imgCartaTemp.getScaledInstance(95,145, Image.SCALE_SMOOTH);
                Image resizedImage = imgCartaTemp.getScaledInstance(84,128, Image.SCALE_SMOOTH);

                //Add new card to the mix
                this.cartas.add(new Carta(face, naipe, getHierarquia(face, naipe), new ImageIcon(resizedImage)));
            }
        }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //Shuffle deck of cards
    public void embaralhar(){
        //Create a new arraylist to hold the shuffled cards temporarily
        ArrayList<Carta> tmpBaralho = new ArrayList<Carta>();
        //Randomly pick from the old deck and copy values to the new deck
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cartas.size();
        for(int i = 0; i<originalSize;i++){
            //gen random num according to int randomNum = rand.nextInt((max - min) + 1) + min;
            randomCardIndex = random.nextInt((this.cartas.size()-1 - 0) + 1) + 0;
            //throw random card into new deck
            tmpBaralho.add(this.cartas.get(randomCardIndex));
            //remove picked from old deck
            this.cartas.remove(randomCardIndex);
        }
        //set this.deck to our newly shuffled deck
        this.cartas = tmpBaralho;
    }

    //Shuffle deck of cards
    public void embaralhar2() {
        Collections.shuffle(this.cartas);
    }

    public int getHierarquia(Face face, Naipe naipe) {

        int hierarquia = -1;

        switch (face)
        {
            case As:
                switch (naipe) {
                    case Espadas:
                        hierarquia = 1;
                        break;

                    case Paus:
                        hierarquia = 2;
                        break;

                    default:
                        hierarquia = 7;
                }
                break;

            case Dois:
                hierarquia = 6;
                break;

            case Tres:
                hierarquia = 5;
                break;

            case Quatro:
                hierarquia = 14;
                break;

            case Cinco:
                hierarquia = 13;
                break;

            case Seis:
                hierarquia = 12;
                break;

            case Sete:
                switch (naipe) {
                    case Espadas:
                        hierarquia = 3;
                        break;

                    case Ouro:
                        hierarquia = 4;
                        break;

                    default:
                        hierarquia = 11;
                }
                break;

            case Dez:
                hierarquia = 10;
                break;

            case Valete:
                hierarquia = 9;
                break;

            case Rei:
                hierarquia = 8;
                break;
        }

        return hierarquia;
    }

    //Use to print out deck
    public String toString(){
        String cardListOutput = "";
        int i = 0;
        for(Carta aCard : this.cartas){
            cardListOutput += "\n" + aCard.toString();
            i++;
        }
        return cardListOutput;
    }

    //Remove a card from the deck
    public void removeCarta(int i){
        this.cartas.remove(i);
    }

    //Get card from deck
    public Carta getCarta(int i){
        return this.cartas.get(i);
    }

    //Add card to deck
    public void addCarta(Carta addCarta){
        this.cartas.add(addCarta);
    }


    //Draw a top card from deck
    public void draw(Baralho monte, int index){
        //Add card to this deck from whatever deck its coming from
        this.cartas.add(monte.getCarta(index));
        //Remove the card in the deck its coming from----NÃO PRECISA REMOVER
        //monte.removeCarta(0);
    }

    public void draw(Carta carta) {
        this.cartas.add(carta);
    }

    public int getSizeBaralho(){
        return this.cartas.size();
    }

    /*public void moverAllToBaralho(Baralho2 monte){
        int thisDeckSize = this.cartas.size();
        //put cards in moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            monte.addCarta(this.getCarta(i));
        }
        //empty out the deck
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCarta(0);
        }
    }*/

    public void devolverCartasMonte(){
        int thisDeckSize = this.cartas.size();
        //empty out the deck
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCarta(0);
        }
    }


    public ImageIcon getVersoCarta() {

        ImageIcon versoCarta = null;

        try {
            URL resource = getClass().getResource("/br/ufsm/trucocbr/res/img/allCards.png");
            File file = new File(resource.toURI());
            BufferedImage imgCompleta = ImageIO.read(file);
            BufferedImage imgCartaTemp;

            imgCartaTemp = imgCompleta.getSubimage(1*largura,4*altura,largura,altura);
            //Image resizedImage = imgCartaTemp.getScaledInstance(115,174, Image.SCALE_SMOOTH);
            //Image resizedImage = imgCartaTemp.getScaledInstance(95,145, Image.SCALE_SMOOTH);
            Image resizedImage = imgCartaTemp.getScaledInstance(84,128, Image.SCALE_SMOOTH);
            versoCarta = new ImageIcon(resizedImage);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return versoCarta;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
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

            for(Carta carta: this.cartas) {
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

        for(Carta carta: this.cartas) {

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
}
