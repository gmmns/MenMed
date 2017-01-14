package edu.upc.pda.clemente.laura.menmed;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = 0L;

    //ATRIBUTS
    private String nom;
        public String getNom() {return nom;}
    private Double quant;
        public Double getQuant() {return quant;}
        public void setQuant(Double quant){this.quant = quant;}

    //CONSTRUCTOR
    public Ingredient(String[] ingr){
        this.quant = Double.parseDouble(ingr[0]);
        this.nom = ingr[1];
    }


}