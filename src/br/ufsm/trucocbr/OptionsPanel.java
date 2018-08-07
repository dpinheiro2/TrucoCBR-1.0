package br.ufsm.trucocbr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class OptionsPanel extends JFrame implements ActionListener{

    JRadioButton computador_button;
    JRadioButton humano_button;
    JRadioButton cbr_button;
    JRadioButton noCbr_button;
    JButton ok;
    JButton cancelar;
    final static int COMPUTADOR = 0;
    final static int HUMANO = 1;
    TrucoCBR trucoCBR;

    public OptionsPanel(TrucoCBR trucoCBR) {
        super("Opções");
        this.trucoCBR = trucoCBR;
        JPanel mainPane = new JPanel(new BorderLayout());
        //mainPane.add(createModePanel(),BorderLayout.NORTH);
        mainPane.add(createMaoPanel(),BorderLayout.CENTER);
        mainPane.add(createButtonPane(),BorderLayout.SOUTH);
        mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setContentPane(mainPane);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ok.addActionListener(this);
        cancelar.addActionListener(this);

    }

    public JPanel createModePanel() {

        JPanel pnlMode = new JPanel(new GridLayout(1,2));
        cbr_button = new JRadioButton("Com CBR", true);
        noCbr_button = new JRadioButton("Sem CBR", true);
        ButtonGroup group = new ButtonGroup();
        group.add(cbr_button);
        group.add(noCbr_button);
        pnlMode.add(cbr_button);
        pnlMode.add(noCbr_button);
        pnlMode.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5,5,5,5),
                BorderFactory.createTitledBorder("Modo")));
        return pnlMode;
    }

    public JPanel createMaoPanel() {

        JPanel pnlMaoInicial = new JPanel(new GridLayout(1,2));
        computador_button = new JRadioButton("Computador", true);
        humano_button = new JRadioButton("Humano", true);
        ButtonGroup group = new ButtonGroup();
        group.add(computador_button);
        group.add(humano_button);
        pnlMaoInicial.add(computador_button);
        pnlMaoInicial.add(humano_button);
        pnlMaoInicial.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5,5,5,5),
                BorderFactory.createTitledBorder("Mão Inicial")));
        return pnlMaoInicial;
    }

    public JPanel createButtonPane(){
        JPanel buttonPane = new JPanel(new BorderLayout());
        JPanel pane = new JPanel(new GridLayout(1,2,5,0));
        pane.add(ok = new JButton("OK"));
        pane.add(cancelar = new JButton("Cancelar"));
        buttonPane.add(pane,BorderLayout.EAST);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return buttonPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == ok){

            trucoCBR.novoJogo();
        }
        setVisible(false);

    }
}
