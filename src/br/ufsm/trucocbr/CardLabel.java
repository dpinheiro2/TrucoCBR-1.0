package br.ufsm.trucocbr;

import javax.swing.*;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class CardLabel extends JLabel {

    private Carta carta;

    public CardLabel(Icon image, Carta carta) {
        super(image);
        this.carta = carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    /*private Face nomeFace;
    private Naipe naipe;
    private int hierarquia;
    private ImageIcon cartaImg;*/

    /*public CardLabel(Icon image, Face nomeFace, Naipe naipe, int hierarquia, ImageIcon cartaImg) {
        super(image);
        this.nomeFace = nomeFace;
        this.naipe = naipe;
        this.hierarquia = hierarquia;
        this.cartaImg = cartaImg;
    }*/



   /* public Face getNomeFace() {
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
    }*/


}
