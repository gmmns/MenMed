package edu.upc.pda.clemente.laura.menmed;


import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class IngrList implements Serializable {
    private Map<String, Ingredient> mapingr = new TreeMap<>();
        public Map<String, Ingredient> getMapingr() {return mapingr;}
        public void setMapingr (Map<String, Ingredient> mapingr) {this.mapingr = mapingr;}

    public IngrList(){
        this.mapingr = new TreeMap<>();
    }


    public String toString(){
        String s = "";
        for(Ingredient i: mapingr.values()){
            s = s + "\n- " + i.toString();
        }
        return s;
    }
}
