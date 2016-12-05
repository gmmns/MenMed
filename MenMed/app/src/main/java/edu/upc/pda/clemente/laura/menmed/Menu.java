package edu.upc.pda.clemente.laura.menmed;


import java.util.TreeMap;

public class Menu {
    /*ATRIBUTS
    - TAULA DE DIES
     */
    private Dia[] menu;
    public Dia[] getMenu() {return menu;}
    public void setMenu(Dia[] menu) {this.menu = menu;}

    private int dies = 7;
	/*CONSTRUCTORS
	- Constructor buit
	- Constructor còpia
	- Constructor omplir
	 */

    public Menu() {
        this.menu = new Dia[dies];
        for (int i=0; i<menu.length; i++){
            this.menu[i].setAaaammdd(0);
            this.menu[i].setDia(new TreeMap <Plat, Boolean>());
        }
    }

    public Menu(Menu m) {
        this.menu = m.menu;
    }

    public Menu(Dia dia1, Dia dia2, Dia dia3, Dia dia4, Dia dia5, Dia dia6, Dia dia7) {
        this.menu = new Dia[dies];

        this.menu[0].setAaaammdd(dia1.getAaaammdd());
        this.menu[0].setDia(new TreeMap<Plat, Boolean>(dia1.getDia()));
        this.menu[1].setAaaammdd(dia2.getAaaammdd());
        this.menu[1].setDia(new TreeMap <Plat, Boolean>(dia2.getDia()));
        this.menu[2].setAaaammdd(dia3.getAaaammdd());
        this.menu[2].setDia(new TreeMap <Plat, Boolean>(dia3.getDia()));
        this.menu[3].setAaaammdd(dia4.getAaaammdd());
        this.menu[3].setDia(new TreeMap <Plat, Boolean>(dia4.getDia()));
        this.menu[4].setAaaammdd(dia5.getAaaammdd());
        this.menu[4].setDia(new TreeMap <Plat, Boolean>(dia5.getDia()));
        this.menu[5].setAaaammdd(dia6.getAaaammdd());
        this.menu[5].setDia(new TreeMap <Plat, Boolean>(dia6.getDia()));
        this.menu[6].setAaaammdd(dia7.getAaaammdd());
        this.menu[6].setDia(new TreeMap <Plat, Boolean>(dia7.getDia()));
    }

}
