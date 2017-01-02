package edu.upc.pda.clemente.laura.menmed;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = 0L;

    private String nom;
        public String getNom() {return nom;}
    private boolean checked = false;
        public boolean isChecked()     { return checked; }
        public void    toggleChecked() { checked = !checked; }
    private String unitats;
        public String getUnitats() {return unitats;}
        public void setUnitats(String unitats) {this.unitats = unitats;}

    public Ingredient(String nom) {
        this.nom = nom;
    }

    public Ingredient(String nom, String unitats) {
        super();
        this.nom = nom;
        this.unitats = unitats;
    }


}