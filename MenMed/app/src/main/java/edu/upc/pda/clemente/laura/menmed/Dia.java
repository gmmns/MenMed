package edu.upc.pda.clemente.laura.menmed;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dia {
    //ATRIBUTS
    private Plat[] dia;
        public Plat[] getDia(){return dia;}
        //public void setDia(String[] dia) {this.dia = dia;}

    //CONSTRUCTOR
    public Dia(String[] plats) {
        this.dia = new Plat[plats.length];
        for (int i = 0; i < plats.length; i++) {
            dia[i] = new Plat(plats[i]);
        }
    }
}
