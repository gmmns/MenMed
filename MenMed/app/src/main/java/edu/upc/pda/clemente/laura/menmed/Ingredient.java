package edu.upc.pda.clemente.laura.menmed;

public class Ingredient {
    private String nom;
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}

    private String unitats;
        public String getUnitats() {return unitats;}
        public void setUnitats(String unitats) {this.unitats = unitats;}

    public Ingredient(String nom, String unitats) {
        super();
        this.nom = nom;
        this.unitats = unitats;
    }

}
