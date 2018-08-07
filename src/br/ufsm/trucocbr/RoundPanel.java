package br.ufsm.trucocbr;

import javax.swing.*;
import java.awt.*;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class RoundPanel extends JLayeredPane {

    private boolean existeCartaComp;
    private boolean existeCartaHumano;
    private Carta cartaComputador;
    private Carta cartaHumano;

    public RoundPanel() {
        this.existeCartaComp = false;
        this.existeCartaHumano = false;
        this.cartaComputador = null;
        this.cartaHumano = null;
    }

    public boolean isExisteCartaHumano() {
        return existeCartaHumano;
    }

    public void setExisteCartaHumano(boolean existeCartaHumano) {
        this.existeCartaHumano = existeCartaHumano;
    }

    public boolean isExisteCartaComp() {
        return existeCartaComp;
    }

    public void setExisteCartaComp(boolean existeCartaComp) {
        this.existeCartaComp = existeCartaComp;
    }

    public Carta getCartaComputador() {
        return cartaComputador;
    }

    public void setCartaComputador(Carta cartaComputador) {
        this.cartaComputador = cartaComputador;
    }

    public Carta getCartaHumano() {
        return cartaHumano;
    }

    public void setCartaHumano(Carta cartaHumano) {
        this.cartaHumano = cartaHumano;
    }

    public boolean isCartasJogadas() {
        if (this.isExisteCartaComp() && this.isExisteCartaHumano() )
            return true;
        else
            return false;
    }

    public int cartaMaior() {
        int maior = -1;
        if (isExisteCartaComp() && isExisteCartaHumano())
            if (this.cartaComputador.getHierarquia() < this.cartaHumano.getHierarquia())
                maior = TrucoData.COMPUTADOR_VEZ;
            else
                maior = TrucoData.HUMANO_VEZ;

        return maior;
    }

    public void ordenaCartasJogadas () {

        if (isCartasJogadas()) {
            Component components[] = this.getComponents();
            CardLabel menor = (CardLabel) components[0];
            for (Component component : components) {
                if (component instanceof CardLabel) {
                    if (menor.getCarta().getHierarquia() > ((CardLabel) component).getCarta().getHierarquia())
                        menor = (CardLabel) component;
                }
            }

            this.setComponentZOrder(menor, 0);
        }
    }

    public void removerCartasJogadas() {
        Component components[] = this.getComponents();
        CardLabel menor = (CardLabel) components[0];
        for (Component component : components) {
            if (component instanceof CardLabel) {
               this.remove(component);
            }
        }
    }

}
