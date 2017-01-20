package edu.upc.pda.clemente.laura.menmed;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String FILENAME = "shopping_list.txt";
    private static final int MAX_BYTES = 10000;

    private ArrayList<Ingredient> itemList = new ArrayList<>();
    private IngrList llista_ingr;
    private IngrList tots_ingr;

    private ListActivityAdapter adapter;

    private ListView list;
    private ImageButton btn_menu;

    //private EditText edit_item;
    //private Button btn_add;

    private int ids_checkbox[] = {R.id.esm_check, R.id.mig_check, R.id.dinar_check1, R.id.dinar_check2, R.id.dinar_check3, R.id.ber_check, R.id.sopar_check1, R.id.sopar_check2, R.id.sopar_check3};
    private int ids_recipes[] = {R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3};

    private String[] all_menu;
    private String[] all_ingr;
    private String[] all_recipes;
    private ImageButton btn_list;
    private ImageButton btn_compr;
    private FloatingActionButton btn_add;
    private EditText add_prod;
    private EditText add_quant;
    private Spinner add_units;


    //protected void onStop() {super.onStop();writeItemList();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        all_ingr = getResources().getStringArray(R.array.all_ingr);
        all_menu = getResources().getStringArray(R.array.all_menu);
        all_recipes = getResources().getStringArray(R.array.all_recipes);
        all_ingr = getResources().getStringArray(R.array.all_ingr);
        add_prod = (EditText) findViewById(R.id.add_prod);
        add_quant = (EditText) findViewById(R.id.add_quant);
        add_units = (Spinner) findViewById(R.id.add_units);
        adapter = new ListActivityAdapter(this, R.layout.shopping_item, itemList);
        tots_ingr = (IngrList) getIntent().getExtras().getSerializable("tots_ingr");
        llista_ingr = (IngrList) getIntent().getExtras().getSerializable("llista_propia");
            //mostrarLlista();
            omplirLlista(llista_ingr);
        ArrayAdapter<CharSequence> adapt_spin = ArrayAdapter.createFromResource(this,R.array.all_units, android.R.layout.simple_spinner_item);
        adapt_spin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_units.setAdapter(adapt_spin);


        /*
        add_prod = (EditText) findViewById(R.id.add_prod);
        add_prod.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addItem();
                return true;
            }
        });
        add_quant = (EditText) findViewById(R.id.add_quant);
        add_quant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addItem();
                return true;
            }
        });
        */

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {
            //afegirIngredient();
        }});
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                itemList.get(pos).toggleChecked();
                adapter.notifyDataSetChanged();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id) {
                eliminarIngredient(pos);
                return true;
            }
        });

    }

    protected void mostrarLlista() {
        itemList.add(new Ingredient("Patates","Kg",false,10.0));
        itemList.add(new Ingredient("Paper WC","u",true,12.0));
        itemList.add(new Ingredient("Aigua","L",false,14.0));
        itemList.add(new Ingredient("Pebrot verd","g",false,500.0));
        itemList.add(new Ingredient("Pomes","Kg",false,3.5));
        itemList.add(new Ingredient("Arròs","Kg",false,2.0));
        itemList.add(new Ingredient("Tomàquets","Kg",false,1.5));
        itemList.add(new Ingredient("Iogurts naturals","u",false,6.0));
        itemList.add(new Ingredient("Maduixes","g",false,800.0));
    }
    protected void omplirLlista(IngrList llista) {
        if(llista == null){}
        else {for(Ingredient i: llista.getMapingr().values()){
            itemList.add(i);
        }}
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


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}
    public void onNothingSelected(AdapterView<?> parent) {}

    //INTERACCIÓ A LA LLISTA
    /*private void afegirIngredient() {
        String prod_text = add_prod.getText().toString();
        String quant_text = add_quant.getText().toString();
        String units_text = add_units.getSelectedItem().toString();

        add_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String quant_text = parent.getItemAtPosition(position).toString();

                //String units_text = add_units.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (!prod_text.isEmpty() && !quant_text.isEmpty() && !units_text.isEmpty()) {
            double quant_num = Double.parseDouble(quant_text);
            itemList.add(new Ingredient(prod_text,units_text,false,quant_num));
            adapter.notifyDataSetChanged();
            add_prod.setText("");
            add_quant.setText("");
            list.smoothScrollToPosition(itemList.size()-1);
        }

        else {
            Toast.makeText(this, R.string.data_missing, Toast.LENGTH_SHORT).show();
            //Snackbar avis = Snackbar.make(View view, "Omple tots els camps obligatoris!", Snackbar.LENGTH_LONG);
            //avis.show();

        }
    }
    private void escriureIngredient(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (int i=0; i<itemList.size(); i++){
                Ingredient in = itemList.get(i);
                String line = String.format("%s;%s;%b;%d\n", in.getNom(), in.getUnitats(), in.isChecked(), in.getQuant());
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Menmed", "writeItemList: FileNotFoundException");
            Toast.makeText(this, R.string.cannot_write, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("Menmed", "writeItemList:IOException");
            Toast.makeText(this, R.string.cannot_write, Toast.LENGTH_SHORT).show();
        }
    }
    private void llegirIngredient(){
        itemList = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            byte[] buffer = new byte[MAX_BYTES];
            int nread = fis.read(buffer);
            String content = new String(buffer, 0, nread);
            String[] lines = content.split("\n");
            for (int i=0; i<lines.length; i++){
                String[] parts = lines[i].split(";");
                itemList.add(new Ingredient(parts[0], parts[1], parts[2].equals("true"), Double.parseDouble(parts[3])));
            }
        } catch (FileNotFoundException e){
            Log.i("Menmed","readItemList: FileNotFoundException");
        } catch (IOException e) {
            Log.e("Menmed", "readItemList:IOException");
            Toast.makeText(this, R.string.cannot_read, Toast.LENGTH_SHORT).show();
        }
    }
    */

    private void eliminarIngredient(final int pos) {
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

}

