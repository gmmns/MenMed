package edu.upc.pda.clemente.laura.menmed;


import android.widget.TextView;

import java.util.TreeMap;

public class Menu {
    //ATRIBUTS
    private Dia[] menu;
        public Dia[] getMenu() {return menu;}
        public void setMenu(Dia[] menu) {this.menu = menu;}

    //CONSTRUCTOR
    public Menu() {
        this.menu = new Dia[7];
        for (int i=0; i<menu.length; i++){
        }
    }
    public Menu(Menu m) {
        this.menu = m.menu;}
    public Menu(String[] all_menu) {
        this.menu = new Dia[all_menu.length];
        for (int i=0; i<all_menu.length; i++){
            String r = all_menu[i];
            String[] parts = r.split(";");
            this.menu[i] = new Dia(parts);
        }

    }

}
