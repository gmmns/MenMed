package edu.upc.pda.clemente.laura.menmed;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class MenuActivity extends AppCompatActivity{
    //ATRIBUTS
    private String[] all_recipes;
    private int ids_recipes[] = {
            R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3
    };
    private Menu menu;
    private Dia dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        all_recipes = getResources().getStringArray(R.array.all_recipes);
        addMenu();
        showDay(menu.getMenu()[1]);
    }

    private void addMenu(){
        this.menu = new Menu(all_recipes);
    }

    private void showDay(Dia dia) {
        for (int i=0; i<ids_recipes.length; i++){
            TextView recept_text = (TextView) findViewById(ids_recipes[i]);
            recept_text.setText(dia.getS_dia()[i]);
        }
    }
}


/*public class MenuActivity extends AppCompatActivity {

    //ATRIBUTS que s'utilitzen en el programa
    private Recepta esmorzar = new Recepta("Torrades amb mermelada");
        private Plat esm = new Plat(esmorzar);
    private Recepta migmati = new Recepta ("Iogurt de fruites");
        private Plat mig = new Plat (migmati);
    private Recepta dinar1 = new Recepta ("Espaguettis al pesto");
        private Plat din1 = new Plat (dinar1);
    private Recepta dinar2 = new Recepta ("Llom a la planxa");
        private Plat din2 = new Plat(dinar2);
    private Recepta dinar3 = new Recepta ("Mandarines");
        private Plat din3 = new Plat(dinar3);
    private Recepta berenar = new Recepta ("Got de llet amb galetes");
        private Plat ber = new Plat(berenar);
    private Recepta sopar1 = new Recepta ("Sopa de galets");
        private Plat sop1 = new Plat(sopar1);
    private Recepta sopar2 = new Recepta ("Capó de Nadal");
        private Plat sop2 = new Plat(sopar2);
    private Recepta sopar3 = new Recepta ("Turrons");
        private Plat sop3 = new Plat(sopar3);

    private Dia dia1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //BUTTON
        ImageButton btn_share = (ImageButton) findViewById(R.id.btn_share);
            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("clicks", "S'ha apretat el botó 'share'");
                }
            });
        Button btn_compr = (Button) findViewById(R.id.btn_compr);
            btn_compr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("clicks", "El botó envia a la llista de la Compra");}
            });
        Button btn_list = (Button) findViewById(R.id.btn_list);
            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("clicks", "El botó canvia a l'activitat'Llista'");
                }
            });
        Button btn_menu = (Button) findViewById(R.id.btn_menu);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("clicks", "El botó canvia a l'activitat 'Menú'");
                }
            });
        Button btn_esb = (Button) findViewById(R.id.btn_esb);
            btn_esb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("clicks", "Esborra tots els indicadors");
                }
            });

        //TEXT VIEW
        TextView esm_recept = (TextView) findViewById(R.id.esm_recept);
            OmplirPlat(esm_recept, esm);
        TextView mig_recept = (TextView) findViewById(R.id.mig_recept);
            OmplirPlat(mig_recept, mig);
        TextView dinar_recept1 = (TextView) findViewById(R.id.dinar_recept1);
            OmplirPlat(dinar_recept1, din1);
        TextView dinar_recept2 = (TextView) findViewById(R.id.dinar_recept2);
            OmplirPlat(dinar_recept2, din2);
        TextView dinar_recept3 = (TextView) findViewById(R.id.dinar_recept3);
            OmplirPlat(dinar_recept3, din3);
        TextView ber_recept = (TextView) findViewById(R.id.ber_recept);
            OmplirPlat(ber_recept, ber);
        TextView sopar_recept1 = (TextView) findViewById(R.id.sopar_recept1);
            OmplirPlat(sopar_recept1, sop1);
        TextView sopar_recept2 = (TextView) findViewById(R.id.sopar_recept2);
            OmplirPlat(sopar_recept2, sop2);
        TextView sopar_recept3 = (TextView) findViewById(R.id.sopar_recept3);
            OmplirPlat(sopar_recept3, sop3);

        //CHECK BOX
        CheckBox esm_check = (CheckBox) findViewById(R.id.esm_check);
        CheckBox mig_check = (CheckBox) findViewById(R.id.mig_check);
        CheckBox dinar_check1 = (CheckBox) findViewById(R.id.dinar_check1);
        CheckBox dinar_check2 = (CheckBox) findViewById(R.id.dinar_check2);
        CheckBox dinar_check3 = (CheckBox) findViewById(R.id.dinar_check3);
        CheckBox ber_check = (CheckBox) findViewById(R.id.ber_check);
        CheckBox sopar_check1 = (CheckBox) findViewById(R.id.sopar_check1);
        CheckBox sopar_check2 = (CheckBox) findViewById(R.id.sopar_check2);
        CheckBox sopar_check3 = (CheckBox) findViewById(R.id.sopar_check3);


        dia1.OmplirDia(esm,mig,din1,din2,din3,ber,sop1,sop2,sop3);


    }

    protected void onStop() {
        super.onStop();
    }

    public void OmplirPlat(TextView tv, Plat plat) {
        tv.setText(plat.getRecepta().getNom());
    }

    public void OmplirDia2(Dia dia){
        TextView esm_recept = (TextView) findViewById(R.id.esm_recept);
        OmplirPlat(esm_recept, esm);
        TextView mig_recept = (TextView) findViewById(R.id.mig_recept);
        OmplirPlat(mig_recept, mig);
        TextView dinar_recept1 = (TextView) findViewById(R.id.dinar_recept1);
        OmplirPlat(dinar_recept1, din1);
        TextView dinar_recept2 = (TextView) findViewById(R.id.dinar_recept2);
        OmplirPlat(dinar_recept2, din2);
        TextView dinar_recept3 = (TextView) findViewById(R.id.dinar_recept3);
        OmplirPlat(dinar_recept3, din3);
        TextView ber_recept = (TextView) findViewById(R.id.ber_recept);
        OmplirPlat(ber_recept, ber);
        TextView sopar_recept1 = (TextView) findViewById(R.id.sopar_recept1);
        OmplirPlat(sopar_recept1, sop1);
        TextView sopar_recept2 = (TextView) findViewById(R.id.sopar_recept2);
        OmplirPlat(sopar_recept2, sop2);
        TextView sopar_recept3 = (TextView) findViewById(R.id.sopar_recept3);
        OmplirPlat(sopar_recept3, sop3);
    }






}
*/

