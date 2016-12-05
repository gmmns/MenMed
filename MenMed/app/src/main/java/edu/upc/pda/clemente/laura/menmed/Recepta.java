package edu.upc.pda.clemente.laura.menmed;

import android.widget.ImageView;

import java.util.Map;


public class Recepta {
    //ATRIBUTS
    private String nom;
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}
    private ImageView fotografia;
        public ImageView getFotografia() {return fotografia;}
        public void setFotografia(ImageView fotografia) {this.fotografia = fotografia;}
    private String elaboració;
        public String getElaboració() {return elaboració;}
        public void setElaboració(String elaboració) {this.elaboració = elaboració;}
    private Map<Ingredient, Double> ingredients;
        public Map<Ingredient, Double> getIngredients() {return ingredients;}
        public void setIngredients(Map<Ingredient, Double> ingredients) {this.ingredients = ingredients;}
    //Ingredient -- ingredient
    //Double  --> quantitat

    //CONSTRUCTORS
    public Recepta(String nom, ImageView fotografia, String elaboració, Map<Ingredient, Double> ingredients) {
        super();
        this.nom = nom;
        this.fotografia = fotografia;
        this.elaboració = elaboració;
        this.ingredients = ingredients;
    }


    public Recepta(String nom){
        super();
        this.nom = nom;
    }

}
