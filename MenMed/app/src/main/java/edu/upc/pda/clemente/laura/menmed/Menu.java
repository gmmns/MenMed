package edu.upc.pda.clemente.laura.menmed;


import android.widget.TextView;

import java.util.TreeMap;

public class Menu {

    private Dia[] menu;
        public Dia[] getMenu() {return menu;}
        public void setMenu(Dia[] menu) {this.menu = menu;}
    private int dies = 7;

    public Menu() {
        this.menu = new Dia[dies];
        for (int i=0; i<menu.length; i++){
            this.menu[i].setAaaammdd(0);
            this.menu[i].setDia(new TreeMap <Plat, Boolean>());
        }
    }

    public Menu(Menu m) {
        this.menu = m.menu;}


    public Menu(String[] all_recipes) {
        this.menu = new Dia[7];
        String r = all_recipes[0]; //de moment posem 0, perquè ens mostri el 1er ítem; després ja pensarem com relacionar l'ítem amb el calendari.
        String[] parts = r.split(";"); //separarem l'ítem en receptes (separades entre elles per ';').

        this.menu[0] = new Dia(parts);

        /*for (int dia=0; dia<7; dia++){
            this.menu[dia] = new Dia();
            this.menu[dia].setAaaammdd(dia+4);
            String r = all_recipes[dia]; //de moment posem 0, perquè ens mostri el 1er ítem; després ja pensarem com relacionar l'ítem amb el calendari.
            String[] parts = r.split(";"); //separarem l'ítem en receptes (separades entre elles per ';').
            this.menu[dia].OmplirDia(parts);
        }*/

    }

}
