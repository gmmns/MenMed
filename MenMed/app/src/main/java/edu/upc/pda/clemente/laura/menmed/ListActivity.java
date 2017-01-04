package edu.upc.pda.clemente.laura.menmed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ImageButton menu_btn;

    private EditText nou_item;
    private ListView llista;
    private ArrayList<Ingredient> items;
    private IngredientAdapter adapter;

    private void dataChanged() {
        adapter.notifyDataSetChanged();
        //saveToFile();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();

    }

    protected void init() {
        menu_btn = (ImageButton) findViewById(R.id.btn_menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*private void removeItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm_erase);
        builder.setMessage(R.string.confirm_message);
        builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                items.remove(pos);
                dataChanged();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    public void onAddItem(View view) {
        String new_item_name = nou_item.getText().toString();
        if (new_item_name.isEmpty()) {
            return;
        }
        items.add(new Ingredient(new_item_name));
        nou_item.setText("");
        dataChanged();
    }

    private static final String DATA_FILE = "itemlist.obj";

    private void saveToFile() {
        try {
            FileOutputStream fos = openFileOutput(DATA_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
        } catch (FileNotFoundException e) {
            Log.e("ShoppingList", "saveToFile: FileNotFoundException");
        } catch (IOException e) {
            Log.e("ShoppingList", "saveToFile: IOException");
        }
    }

    private void restoreFromFile() throws IOException {
        try {
            FileInputStream fis = openFileInput(DATA_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<Ingredient>)ois.readObject();
        } catch (ClassNotFoundException e) {
            Log.e("ShoppingList", "restoreFromFile: ClassNotFoundException");
        } catch (OptionalDataException e) {
            Log.e("ShoppingList", "restoreFromFile: OptionalDataException");
        } catch (StreamCorruptedException e) {
            Log.e("ShoppingList", "restoreFromFile: StreamCorruptedException");
        }
    }



    }*/

}
