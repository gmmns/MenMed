package edu.upc.pda.clemente.laura.menmed;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    private List<Ingredient> ingr_list = new ArrayList<Ingredient>();
        public List<Ingredient> getIngr_list(){return ingr_list;}
        public void setIngr_list(List<Ingredient> ingr_list) {this.ingr_list = ingr_list;}


    //CONSTRUCTORS
    public Recepta (String[] parts) {
        this.nom = parts[0];
        this.elaboracio = parts[1];
        this.ingr_list = new ArrayList<Ingredient>();
        String r = parts[2];
        String[] ingr = r.split("-");
        for(int i=0; i<ingr.length; i++){
            String s = ingr[i];
            String[] part = s.split("/");
            ingr_list.add(new Ingredient(part));
        }
    }
    public Recepta(String nom){
        super();
        this.nom = nom;
        this.fotografia = null;
        this.elaboracio = "";
        this.ingr_list = new ArrayList<Ingredient>(){};
    }
    public Recepta(){
        super();
        this.nom = "";
        this.fotografia = null;
        this.elaboracio = "";
        this.ingr_list = new ArrayList<Ingredient>(){};
    }


    //MÈTODES
    public String toString(){
        String s = "Nom:\n" + nom + "\nElaboració:\n" + elaboracio + "\nIngredients:";
        for(int x=0;x<ingr_list.size();x++) {
            s = s + "\n- " + ingr_list.get(x).getQuant() + " de " + ingr_list.get(x).getNom();
        }
        return s;
    }
}
