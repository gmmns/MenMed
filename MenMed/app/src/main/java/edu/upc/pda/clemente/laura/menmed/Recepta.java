package edu.upc.pda.clemente.laura.menmed;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Recepta {
    //ATRIBUTS
    private String nom;
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}
    private ImageView fotografia;
        public ImageView getFotografia() {return fotografia;}
        public void setFotografia(ImageView fotografia) {this.fotografia = fotografia;}
    private String elaboracio;
        public String getElaboracio() {return elaboracio;}
        public void setElaboracio(String elaboracio) {this.elaboracio = elaboracio;}
    private Map<Ingredient, Double> ingredients;
        public Map<Ingredient, Double> getIngredients() {return ingredients;}
        public void setIngredients(Map<Ingredient, Double> ingredients) {this.ingredients = ingredients;}
    //Ingredient -- ingredient
    //Double  --> quantitat

    //CONSTRUCTORS
    public Recepta(String nom, ImageView fotografia, String elaboracio, Map<Ingredient, Double> ingredients) {
        super();
        this.nom = nom;
        this.fotografia = fotografia;
        this.elaboracio = elaboracio;
        this.ingredients = ingredients;
    }


    public Recepta(String nom){
        super();
        this.nom = nom;
        this.fotografia = null;
        this.elaboracio = "";
        this.ingredients = new TreeMap<Ingredient, Double>() {};
    }

    public Recepta(){
        super();
        this.nom = "";
        this.fotografia = null;
        this.elaboracio = "";
        this.ingredients = new TreeMap<Ingredient, Double>() {};
    }

}
