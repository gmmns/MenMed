package edu.upc.pda.clemente.laura.menmed;

public class Plat {
    //ATRIBUTS
    private Recepta recepta;
        public Recepta getRecepta() {return recepta;}
        public void setRecepta(Recepta recepta) {this.recepta = recepta;}

    private int comensals;
        public int getComensals() {return comensals;}
        public void setComensals(int comensals) {this.comensals = comensals;}

    //CONSTRUCTOR
    public Plat(){
        super();
        this.recepta = new Recepta();
        this.comensals = 0;
    }
    public Plat(String nom){
        super();
        this.recepta = new Recepta(nom);
        this.comensals = 0;
    }
    public Plat(Recepta recepta, int comensals) {
        super();
        this.recepta = recepta;
        this.comensals = comensals;
    }
    public Plat(Recepta r){
        super();
        this.recepta = r;
        this.comensals = 0;
    }


}
