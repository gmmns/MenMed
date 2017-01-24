package edu.upc.pda.clemente.laura.menmed;

import java.lang.Object;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<Ingredient> itemList = new ArrayList<>();
    private IngrList llista_ingr;
    private IngrList tots_ingr;
    private Intent intent;

    private static final String FITXER = "llista.obj";

    private ListActivityAdapter adapter;

    private ListView list;
    private ImageButton btn_menu;
    private ImageButton delete_all;
    private ImageButton shopping_done;

    private FloatingActionButton btn_add;

    private EditText add_prod;
    private EditText add_quant;
    private Spinner add_units;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //inicialitzar i relacionar layout i codi
        init();
        add_prod = (EditText) findViewById(R.id.add_prod);
        add_quant = (EditText) findViewById(R.id.add_quant);
        add_units = (Spinner) findViewById(R.id.add_units);

        try {recuperar();
        } catch (IOException e) {
            llista_ingr = new IngrList();
            omplirLlista(llista_ingr);
        }

        //Omplir dades amb informació MenuActivity
        tots_ingr = (IngrList) getIntent().getExtras().getSerializable("llista_sencera");
        llista_ingr = (IngrList) getIntent().getExtras().getSerializable("llista_propia");
        omplirLlista(llista_ingr);

        //accions llista de la compra
        adapter = new ListActivityAdapter(this, R.layout.shopping_item, itemList);
        adapter.notifyDataSetChanged();
        ArrayAdapter<CharSequence> adapt_spin = ArrayAdapter.createFromResource(this,R.array.all_units, android.R.layout.simple_spinner_item);
        adapt_spin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_units.setAdapter(adapt_spin);
        add_prod = (EditText) findViewById(R.id.add_prod);
            add_prod.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {afegirIngredient();return true;}
        });
        add_quant = (EditText) findViewById(R.id.add_quant);
            add_quant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {afegirIngredient();return true;}
        });

        list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
            btn_add.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {
            afegirIngredient();
        }});
        delete_all = (ImageButton) findViewById(R.id.btn_delete_all);
            delete_all.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {
            esborraLlista();
        }});
        shopping_done = (ImageButton) findViewById(R.id.btn_shopping_done);
            shopping_done.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {
            compraFeta();
        }});
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                itemList.get(pos).toggleChecked();
                adapter.notifyDataSetChanged();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id) {eliminarIngredient(pos);return true;}});

        guardar();
    }
    protected void omplirLlista(IngrList llista) {
        if(llista == null){}
        else {for(Ingredient i: llista.getMapingr().values()){
            itemList.add(i);
            //afegirUnitats(itemList);
            }
        }
        guardar();
    }
    private void esborraLlista() {
        itemList.removeAll(itemList);
        adapter.notifyDataSetChanged();

    }
    private void compraFeta() {
        for(int i=0; i<itemList.size();i++){
            if(itemList.get(i).isChecked()){
                itemList.remove(i);
                adapter.notifyDataSetChanged();
            } else {}
        }
    }


    private ArrayList<Ingredient> afegirUnitats(ArrayList<Ingredient> llista){
        for(int i=0; i<llista.size(); i++){
            if(!tots_ingr.getMapingr().containsKey(llista.get(i).getNom())){
                llista.get(i).setUnitats(trobarUnitats(llista.get(i)));
            }
        }
        return llista;
    }
    private String trobarUnitats(Ingredient ingr){
        if(tots_ingr.getMapingr().containsKey(ingr.getNom())){
            return tots_ingr.getMapingr().get(ingr.getNom()).getUnitats();
        }
        return null;
    }

    //MÈTODE QUE PERMET CANVIAR D'ACTIVITAT
    protected void init(){
        btn_menu = (ImageButton) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                guardar();
                finish();
            }});

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}
    public void onNothingSelected(AdapterView<?> parent) {}

    //INTERACCIÓ A LA LLISTA

    private void afegirIngredient() {
        String prod_text = add_prod.getText().toString();
        String quant_text = add_quant.getText().toString();
        String units_text = add_units.getSelectedItem().toString();
        Boolean sum_quant = false;

        add_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String quant_text = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (!prod_text.isEmpty() && !quant_text.isEmpty() && !units_text.isEmpty()) {
            double quant_num = Double.parseDouble(quant_text);
            for (int i=0; i<itemList.size(); i++){
                if (itemList.get(i).getNom().equals(prod_text)){
                    sum_quant = true;
                    Double new_num = itemList.get(i).getQuant();
                    itemList.get(i).setQuant(new_num+quant_num);
                    adapter.notifyDataSetChanged();
                    add_prod.setText("");
                    add_quant.setText("");
                }
            }
            if (sum_quant.equals(false)){
                itemList.add(new Ingredient(prod_text,units_text,false,quant_num));
                adapter.notifyDataSetChanged();
                add_prod.setText("");
                add_quant.setText("");
            }
        }
        else {Toast.makeText(this, R.string.data_missing, Toast.LENGTH_SHORT).show();}

    }

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

    //PERSISTÈNCIA
    //recuperar i crear l'arxiu extern
    protected void onStop() {super.onStop();guardar();}
    private void guardar() {
        try {
            FileOutputStream fos = openFileOutput(FITXER, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(llista_ingr);
        } catch (FileNotFoundException e) {
            Log.e("Llista", "guardar: FileNotFoundException");
            Toast.makeText(this, R.string.cannot_write, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Llista", "guardar: IOException");
            Toast.makeText(this, R.string.cannot_write, Toast.LENGTH_SHORT).show();
        }
    }
    private void recuperar() throws IOException {
        try {
            FileInputStream fis = openFileInput(FITXER);
            ObjectInputStream ois = new ObjectInputStream(fis);
            llista_ingr = (IngrList)ois.readObject();
            omplirLlista(llista_ingr);
        } catch (ClassNotFoundException e) {
            Log.e("Llista", "Recuperar: ClassNotFoundException");
        } catch (OptionalDataException e) {
            Log.e("Llista", "Recuperar: OptionalDataException");
        } catch (StreamCorruptedException e) {
            Log.e("Llista", "Recuperar: StreamCorruptedException");
        }
    }

}

