package edu.upc.pda.clemente.laura.menmed;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ListActivity extends AppCompatActivity {

    private String[] all_ingr;
    private ArrayList<IngrList> itemList = new ArrayList<IngrList>();
    private ListActivityAdapter adapter;

    private ListView list;
    private ImageButton btn_menu;
    /*
    private EditText edit_item;
    private Button btn_add;
    */

    private int ids_checkbox[] = {R.id.esm_check, R.id.mig_check, R.id.dinar_check1, R.id.dinar_check2, R.id.dinar_check3, R.id.ber_check, R.id.sopar_check1, R.id.sopar_check2, R.id.sopar_check3};
    private int ids_recipes[] = {R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3};

    private String[] all_menu;
    private String[] all_ingr;
    private String[] all_recipes;
    private ImageButton btn_list;
    private ImageButton btn_compr;
    Recepta[] receptari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        all_ingr = getResources().getStringArray(R.array.all_ingr);
        crearLlista();
        all_menu = getResources().getStringArray(R.array.all_menu);
        all_recipes = getResources().getStringArray(R.array.all_recipes);
        all_ingr = getResources().getStringArray(R.array.all_ingr);

        Intent intent = new Intent(getBaseContext(), MenuActivity.class);

        this.receptari = (Recepta[]) getIntent().getSerializableExtra("receptari");
        //send_to_list();



        itemList = new ArrayList<IngrList>();

        itemList.add(new IngrList("Patates","u",false,10.0));
        itemList.add(new IngrList("Paper WC","u",true,20.0));
        itemList.add(new IngrList("Patates","u",false,10.0));
        itemList.add(new IngrList("Paper WC","u",false,20.0));
        itemList.add(new IngrList("Patates","u",false,10.0));
        itemList.add(new IngrList("Paper WC","u",false,20.0));
        itemList.add(new IngrList("Patates","u",false,10.0));
        itemList.add(new IngrList("Paper WC","u",false,20.0));



        adapter = new ListActivityAdapter(
                this,
                R.layout.shopping_item,
                itemList
        );

        /*
        edit_item = (EditText) findViewById(R.id.edit_item);
        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addItem();
                return true;
            }
        });
        */

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        /*
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
        */

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                itemList.get(pos).toggleChecked();
                adapter.notifyDataSetChanged();
            }
        });
        /*
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id) {
                maybeRemoveItem(pos);
                return true;
            }
        });
        */
    }



    //MÈTODE QUE PERMET ENVIAR ELS PLATS A LA LLISTA
    protected void send_to_list(){
        btn_compr = (ImageButton) findViewById(R.id.btn_compr);
        btn_compr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (find_concidence()==null){
                    Toast.makeText(ListActivity.this, "No has sel·leccionat cap recepta", Toast.LENGTH_LONG).show();
                } else {
                    omplirLlista(find_concidence());
                    //IngrList(find_concidence().getNom(),find_concidence().getIngr_list(){

                }
            }

        });
    }



    protected void omplirLlista(ArrayList<Ingredient> llista) {
        this.itemList = new ArrayList<IngrList>();
        for(int i=0; i<llista.size(); i++){
            IngrList ingr = new IngrList(llista.get(i).getNom(), "u", false, llista.get(i).getQuant());
            itemList.add(ingr);
        }
    }
    //Mètode que em troba la recepta que està clicada
    protected ArrayList<Ingredient> find_concidence() {
        for (int i = 0; i < ids_checkbox.length; i++) {
            CheckBox check = (CheckBox) findViewById(ids_checkbox[i]);
            if (check.isChecked() == true) {
                TextView rec = (TextView) findViewById(ids_recipes[i]);
                for (int j = 0; j < all_recipes.length; j++) {
                    if (rec.getText().equals(receptari[j].getNom())) {
                        return receptari[j].getIngr_list();
                    };
                }
            }
        }
        return null;
    }

    //MÈTODE QUE PERMET CANVIAR D'ACTIVITAT
    protected void init(){
        btn_menu = (ImageButton) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ListActivity.this, MenuActivity.class);
                startActivity(in);
            }
        });
    }

    private void crearLlista(){
        this.itemList = new ArrayList<IngrList>();
        for (int i=0; i<all_ingr.length; i++){
            String r = this.all_ingr[i];
            String[] parts = r.split(";");
            IngrList ingr = new IngrList(parts[0], parts[1]);
            itemList.add(ingr);
        }
    }

    /*
    private void maybeRemoveItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        String msg = getResources().getString(R.string.confirm_remove);
        builder.setMessage(String.format(msg, itemList.get(pos)));
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemList.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
    */

    /*
    private void addItem() {
        String item_text = edit_item.getText().toString();
        if (!item_text.isEmpty() !item_text.equals("") ) {
            itemList.add(item_text);
            adapter.notifyDataSetChanged();
            edit_item.setText("");
        }
    }
    */
}


