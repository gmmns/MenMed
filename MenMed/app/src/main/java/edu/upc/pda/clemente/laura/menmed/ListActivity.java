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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<IngrList> itemList;
    private ListActivityAdapter adapter;

    private ListView list;
    private ImageButton btn_menu;
    /*
    private EditText edit_item;
    private Button btn_add;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();

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


