package br.ufsm.trucocbr;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Universidade Federal de Santa Maria
 * Aprendizagem de Máquina
 * Daniel Vargas
 * Criado em 27/10/2017.
 */


public class Resource {

    protected static ResourceBundle resources;
    static {
        try {
            resources = ResourceBundle.getBundle("br.ufsm.trucocbr.res.TrucoProperties", Locale.getDefault());
        } catch (Exception e) {
            System.out.println("Arquivo de propriedades do jogo não encontrado");
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Arquivo de propriedades do jogo não encontrado",
                    "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public String getResourceString(String key){
        String str;
        try{
            str = resources.getString(key);
        }catch(Exception e){
            str = null;
        }
        return str;
    }
    protected URL getResource(String key){
        String name = getResourceString(key);
        if(name != null){
            URL url = this.getClass().getResource(name);
            return url;
        }
        return null;
    }
}

