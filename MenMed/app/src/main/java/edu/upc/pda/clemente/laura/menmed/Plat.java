package edu.upc.pda.clemente.laura.menmed;

public class Plat {
        //ATRIBUTS
        private String recepta;
            public String getRecepta() {return recepta;}
            public void setRecepta(String recepta) {this.recepta = recepta;}

        private int comensals;
            public int getComensals() {return comensals;}
            public void setComensals(int comensals) {this.comensals = comensals;}

        private boolean check_plat;
            public boolean getCheckPlat(){return check_plat;}
            public void setCheckPlat(boolean check_plat){this.check_plat = check_plat;}

        public Plat(String recepta){
            this.recepta = recepta;
            this.comensals = 0;
            this.check_plat = false;
        }

}
