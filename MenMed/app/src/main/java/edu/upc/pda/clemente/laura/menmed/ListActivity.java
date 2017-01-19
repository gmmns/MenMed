package edu.upc.pda.clemente.laura.menmed;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    private ArrayList<IngrList> itemList = new ArrayList<IngrList>();
    private ListActivityAdapter adapter;

    private ListView list;
    private ImageButton btn_menu;

    /*private void writeItemList(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (int i=0; i<itemList.size(); i++){
                IngrList in = itemList.get(i);
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
    */

    /*private void readItemList(){
        itemList = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            byte[] buffer = new byte[MAX_BYTES];
            int nread = fis.read(buffer);
            String content = new String(buffer, 0, nread);
            String[] lines = content.split("\n");
            for (int i=0; i<lines.length; i++){
                String[] parts = lines[i].split(";");
                itemList.add(new IngrList(parts[0], parts[1], parts[2].equals("true"), Double.parseDouble(parts[3])));
            }
        } catch (FileNotFoundException e){
            Log.i("Menmed","readItemList: FileNotFoundException");
        } catch (IOException e) {
            Log.e("Menmed", "readItemList:IOException");
            Toast.makeText(this, R.string.cannot_read, Toast.LENGTH_SHORT).show();
        }
    }
    */
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
    private FloatingActionButton btn_add;
    private EditText add_prod;
    private EditText add_quant;
    private Spinner add_units;
    Recepta[] receptari;


    /*
    @Override
    protected void onStop() {
        super.onStop();
        writeItemList();
    }
    */

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
        add_prod = (EditText) findViewById(R.id.add_prod);
        add_quant = (EditText) findViewById(R.id.add_quant);
        add_units = (Spinner) findViewById(R.id.add_units);

        ArrayAdapter<CharSequence> adapt_spin = ArrayAdapter.createFromResource(this,R.array.all_units, android.R.layout.simple_spinner_item);
        adapt_spin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_units.setAdapter(adapt_spin);


        /*Intent intent = new Intent(getBaseContext(), MenuActivity.class);

        itemList = new ArrayList<IngrList>();
        this.receptari = (Recepta[]) getIntent().getSerializableExtra("receptari");
        */

        //send_to_list();


        //readItemList();

        itemList = new ArrayList<IngrList>();

        itemList.add(new IngrList("Patates","Kg",false,10.0));
        itemList.add(new IngrList("Paper WC","u",true,12.0));
        itemList.add(new IngrList("Aigua","L",false,14.0));
        itemList.add(new IngrList("Pebrot verd","g",false,500.0));
        itemList.add(new IngrList("Pomes","Kg",false,3.5));
        itemList.add(new IngrList("Arròs","Kg",false,2.0));
        itemList.add(new IngrList("Tomàquets","Kg",false,1.5));
        itemList.add(new IngrList("Iogurts naturals","u",false,6.0));
        itemList.add(new IngrList("Maduixes","g",false,800.0));




        adapter = new ListActivityAdapter(
                this,
                R.layout.shopping_item,
                itemList
        );

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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });


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


    private void addItem() {
        String prod_text = add_prod.getText().toString();
        String quant_text = add_quant.getText().toString();
        String units_text = add_units.getSelectedItem().toString();
        /*
        add_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String quant_text = parent.getItemAtPosition(position).toString();

                //String units_text = add_units.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        if (!prod_text.isEmpty() && !quant_text.isEmpty() && !units_text.isEmpty()) {
            double quant_num = Double.parseDouble(quant_text);
            itemList.add(new IngrList(prod_text,units_text,false,quant_num));
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

