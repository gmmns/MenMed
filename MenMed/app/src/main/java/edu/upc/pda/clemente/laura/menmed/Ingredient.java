package edu.upc.pda.clemente.laura.menmed;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = 0L;
    //ATRIBUTS
    private String nom;
        public String getNom() {return nom;}
    private String unitats;
        public String getUnitats() {return unitats;}
        public void setUnitats(String unitats) {this.unitats = unitats;}
    private Double quant;
        public Double getQuant() {return quant;}
        public void setQuant(Double quant){this.quant = quant;}
    private boolean checked;
        public Boolean isChecked() {return checked;}
        public void toggleChecked() {this.checked = !checked;}

    //CONSTRUCTORS
    //Crear Ingredient a partir de la llista total d'ingredients
    public Ingredient(String nom, String unitats){
        this.nom = nom;
        this.unitats = unitats;
        this.checked = false;
        this.quant = 0.0;
    }
    //Crear Ingredient introduïnt-lo
    public Ingredient(String nom, String unitats, Boolean checked, Double quant){
        this.nom = nom;
        this.unitats = unitats;
        this.checked = checked;
        this.quant = quant;
    }
    //Crear Ingredient enviant-lo des de menú
    public Ingredient(String[] ingr){
        if(ingr.length==2){
            if(esDecimal(ingr[0])) this.quant = Double.parseDouble(ingr[0]);
            if(ingr[1]!=null) this.nom = ingr[1];
        }

    }



    //MÈTODES
    public String toString(){
        return this.nom + ": " + this.quant + " " + this.unitats;
    }

    public boolean esDecimal(String cad) {
        try
        {
            Double.parseDouble(cad);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }
}