package edu.upc.pda.clemente.laura.menmed;


import android.widget.TextView;

import java.util.TreeMap;

public class Menu {

    private Dia[] menu;
        public Dia[] getMenu() {return menu;}
        public void setMenu(Dia[] menu) {this.menu = menu;}
    //private int dies = 7;

    public Menu() {
        this.menu = new Dia[7];
        for (int i=0; i<menu.length; i++){
            this.menu[i].setAaaammdd(0);
            this.menu[i].setDia(new TreeMap <Plat, Boolean>());
        }
    }

    public Menu(Menu m) {
        this.menu = m.menu;}


    public Menu(String[] all_recipes) {
        this.menu = new Dia[all_recipes.length];
        for (int dia=0; dia<all_recipes.length; dia++){
            String r = all_recipes[dia];
            String[] parts = r.split(";");
            this.menu[dia] = new Dia(parts);
        }

    }

}
