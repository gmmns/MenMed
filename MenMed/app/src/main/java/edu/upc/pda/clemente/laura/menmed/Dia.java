package edu.upc.pda.clemente.laura.menmed;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Dia {
    //ATRIBUTS
    private int aaaammdd;
    public int getAaaammdd() {return aaaammdd;}
    public void setAaaammdd(int aaaammdd) {this.aaaammdd = aaaammdd;}

    private Map<Plat, Boolean> dia;
        public Map<Plat, Boolean> getDia() {return dia;}
        public void setDia(Map<Plat, Boolean> dia) {this.dia = dia;}


    //CONSTRUCTOR
    public Dia(int aaaammdd, Map<Plat, Boolean> dia) {
        super();
        this.aaaammdd = aaaammdd;
        this.dia = dia;
    }

    public Dia() {
        super();
        this.aaaammdd = new Integer(aaaammdd);
        this.dia = new Map<Plat, Boolean>() {
            @Override
            public void clear() {
            }

            @Override
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

            public void putAll(Map<? extends Plat, ? extends Boolean> map) {

            }

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

    public Dia(Plat plat1, Plat plat2, Plat plat3, Plat plat4, Plat plat5, Plat plat6, Plat plat7, Plat plat8, Plat plat9){

    }

}
