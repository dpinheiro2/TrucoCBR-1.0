package br.ufsm.trucocbr;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Carta {
    private Face nomeFace;
    private Naipe naipe;
    private int hierarquia;
    private ImageIcon cartaImg;

    public Carta(Face nomeFace, Naipe naipe, int hierarquia, ImageIcon cartaImg) {
        this.nomeFace = nomeFace;
        this.naipe = naipe;
        this.hierarquia = hierarquia;
        this.cartaImg = cartaImg;
    }

    public Face getNomeFace() {
        return nomeFace;
    }

    public void setNomeFace(Face nomeFace) {
        this.nomeFace = nomeFace;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }

    public int getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(int hierarquia) {
        this.hierarquia = hierarquia;
    }

    public ImageIcon getCartaImg() {
        return cartaImg;
    }

    public void setCartaImg(ImageIcon cartaImg) {
        this.cartaImg = cartaImg;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nomeFace=" + nomeFace +
                ", naipe=" + naipe +
                ", hierarquia=" + hierarquia +
                ", cartaImg=" + cartaImg +
                '}';
    }

    public int getPontosCarta(Face face) {
        int ptsCarta = 0;

        switch (face){
            case As:
                ptsCarta = 1;
                break;
            case Dois:
                ptsCarta = 2;
                break;
            case Tres:
                ptsCarta = 3;
                break;
            case Quatro:
                ptsCarta = 4;
                break;
            case Cinco:
                ptsCarta = 5;
                break;
            case Seis:
                ptsCarta = 6;
                break;
            case Sete:
                ptsCarta = 7;
                break;
            case Dez:
                ptsCarta = 0;
                break;
            case Valete:
                ptsCarta = 0;
                break;
            case Rei:
                ptsCarta = 0;
                break;
        }
        return ptsCarta;
    }
}
