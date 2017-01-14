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
    public Ingredient(String[] parts){
        String[] ingr = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String r = parts[i];
            ingr = r.split("/");
        }
        //this.quant = Double.valueOf(ingr[0]).doubleValue();
        this.nom = ingr[1];

    }


}