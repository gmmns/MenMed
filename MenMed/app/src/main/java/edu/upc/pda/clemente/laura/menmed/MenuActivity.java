package edu.upc.pda.clemente.laura.menmed;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    //ATRIBUTS que s'utilitzen en el programa
    private Dia dia1;
    private Dia dia2;


    private Recepta recepta1 = new Recepta("Torrades amb mermelada");
    private Plat plat1 = new Plat (recepta1);

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
                    Log.i("clicks", "El botó envia a la llista de la Compra");                }
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
            esm_recept.setText(plat1.getRecepta().getNom());
        TextView mig_recept = (TextView) findViewById(R.id.mig_recept);
        TextView dinar_recept1 = (TextView) findViewById(R.id.dinar_recept1);
        TextView dinar_recept2 = (TextView) findViewById(R.id.dinar_recept2);
        TextView dinar_recept3 = (TextView) findViewById(R.id.dinar_recept3);
        TextView ber_recept = (TextView) findViewById(R.id.ber_recept);
        TextView sopar_recept1 = (TextView) findViewById(R.id.sopar_recept1);
        TextView sopar_recept2 = (TextView) findViewById(R.id.sopar_recept2);
        TextView sopar_recept3 = (TextView) findViewById(R.id.sopar_recept3);

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



    }

    protected void onStop() {
        super.onStop();
    }

    public Ingredient afegirIngredient (String nom, String unitats) {
        return null;

    }




}




