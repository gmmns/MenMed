package edu.upc.pda.clemente.laura.menmed;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dia {
    //ATRIBUTS
    private int aaaammdd;
        public int getAaaammdd() {return aaaammdd;}
        public void setAaaammdd(int aaaammdd) {this.aaaammdd = aaaammdd;}

    private String[] s_dia;
        public String[] getS_dia(){return s_dia;}
        public void setS_dia(String[] s_dia) {this.s_dia = s_dia;}

    private TreeMap<Plat, Boolean> dia;
        public TreeMap<Plat, Boolean> getDia() {return dia;}
        public void setDia(TreeMap<Plat, Boolean> dia) {this.dia = dia;}



    //CONSTRUCTOR
    public Dia(int aaaammdd, TreeMap<Plat, Boolean> dia) {
        super();
        this.aaaammdd = aaaammdd;
        this.dia = dia;
    }

    public Dia() {
        super();
        this.aaaammdd = 0;
        this.dia = new TreeMap<Plat, Boolean>() {
            public void clear() {}
            public boolean containsKey(Object key) {
                return false;
            }
            public boolean containsValue(Object value) {
                return false;
            }
            public Set<Entry<Plat, Boolean>> entrySet() {
                return null;
            }
            public Boolean get(Object key) {
                return null;
            }
            public boolean isEmpty() {
                return false;
            }
            public Set<Plat> keySet() {
                return null;
            }
            public Boolean put(Plat key, Boolean value) {
                return null;
            }
            public void putAll(Map<? extends Plat, ? extends Boolean> map) {}
            public Boolean remove(Object key) {
                return null;
            }
            public int size() {
                return 0;
            }
            public Collection<Boolean> values() {
                return null;
            }
        };
    }

    public Dia(String[] plats) {
        this.aaaammdd = 0;
        this.s_dia = new String[plats.length];
        for (int i = 0; i < plats.length; i++) {
            s_dia[i] = plats[i];
        }
        //afegirAMap(plats);
    }

    public void afegirAMap (String[] plats) {
        this.dia = new TreeMap<Plat, Boolean>();
        for (int i=0; i<plats.length; i++){
            Plat plat = new Plat(plats[i]);
            this.dia.put(plat, false);
        }
    }

}
