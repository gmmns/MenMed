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
    private IngrList llista;
        public IngrList getLlista(){return llista;}
        public void setLlista(IngrList llista){this.llista = llista;}


    //CONSTRUCTORS
    public Recepta (String[] parts) {
        this.nom = parts[0];
        this.elaboracio = parts[1];
        this.llista = new IngrList();
        String r = parts[2];
        String[] ingr = r.split("-");
        for(int i=0; i<ingr.length; i++){
            String s = ingr[i];
            String[] part = s.split("/");
            llista.getMapingr().put(ingr[i], new Ingredient(part));
        }
    }
    public Recepta(String nom){
        super();
        this.nom = nom;
        this.fotografia = null;
        this.elaboracio = "";
        this.llista = new IngrList();
    }
    public Recepta(){
        super();
        this.nom = "";
        this.fotografia = null;
        this.elaboracio = "";
        this.llista = new IngrList();
    }


    //MÈTODES
    public String toString(){
        String s = "Nom:\n" + nom + "\nElaboració:\n" + elaboracio + "\nIngredients:" + toStringIngr();
        return s;
    }

    public String toStringIngr(){
        String s = "";
        for(Ingredient i: llista.getMapingr().values()){
            s = s + "\n- " + i.getQuant() + " de " + i.getNom();
        }
        return s;
    }
}
