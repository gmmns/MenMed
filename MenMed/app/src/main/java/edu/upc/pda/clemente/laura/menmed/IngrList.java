package edu.upc.pda.clemente.laura.menmed;

/**
 * Created by Gemma on 14/1/17.
 */

public class IngrList {
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
    //Crear Ingredient a partir de la llista emmagatzemada
    public IngrList(String nom, String unitats){
        this.nom = nom;
        this.unitats = unitats;
        this.checked = false;
        this.quant = 0.0;
    }
    //Crear Ingredient introduïnt-lo
    public IngrList(String nom, String unitats, Boolean checked, Double quant){
        this.nom = nom;
        this.unitats = unitats;
        this.checked = checked;
        this.quant = quant;
    }

    //Crear Ingredient enviant-lo des de menú
    public IngrList(String nom, String unitats, Double quant){
        this.nom = nom;
        this.unitats = unitats;
        this.checked = false;
        this.quant = quant;
    }

    //MÈTODES
    public String toString(){
        return this.nom + "[" + this.quant + " " + this.unitats + "]";
    }
}
