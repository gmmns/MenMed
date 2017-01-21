package edu.upc.pda.clemente.laura.menmed;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.android.datetimepicker.date.DatePickerDialog;
import com.ramzcalender.RWeekCalendar;
import com.ramzcalender.listener.CalenderListener;
import com.ramzcalender.utils.CalUtil;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableDateTime;
import org.joda.time.Weeks;

import java.util.Calendar;
import java.util.TreeMap;

public class MenuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //ATRIBUTS
    private Menu menu;
    private Recepta[] receptes;
        public Recepta[] getReceptes() {return receptes;}
        public void setReceptes(Recepta[] receptes) {this.receptes = receptes;}

    private static final String FITXER = "llista.obj";

    private IngrList tots_ingr;
    private IngrList llista_ingr;
    private Intent intent;

    private int ids_recipes[] = {R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3};
        private TextView TVEsm, TVMig, TVDin1, TVDin2, TVDin3, TVBer, TVSop1, TVSop2, TVSop3;
        private TextView TV_recipes[] = {TVEsm, TVMig, TVDin1, TVDin2, TVDin3, TVBer, TVSop1, TVSop2, TVSop3};

    private int ids_checkbox[] = {R.id.esm_check, R.id.mig_check, R.id.dinar_check1, R.id.dinar_check2, R.id.dinar_check3, R.id.ber_check, R.id.sopar_check1, R.id.sopar_check2, R.id.sopar_check3};
        private CheckBox esm_check, mig_check, dinar_check1, dinar_check2, dinar_check3, ber_check, sopar_check1, sopar_check2, sopar_check3;
        private CheckBox[] checks = {esm_check, mig_check, dinar_check1, dinar_check2, dinar_check3, ber_check, sopar_check1, sopar_check2, sopar_check3};

    private int ids_comensals[] = {R.id.esm_com, R.id.mig_com, R.id.dinar_com1, R.id.dinar_com2, R.id.dinar_com3, R.id.ber_com, R.id.sopar_com1, R.id.sopar_com2, R.id.sopar_com3};
        private TextView comEsm, comMig, comDin1, comDin2, comDin3, comBer, comSop1, comSop2, comSop3;
        private TextView[] TV_com = {comEsm, comMig, comDin1, comDin2, comDin3, comBer, comSop1, comSop2, comSop3};
    private int ids_less[] = {R.id.esm_less, R.id.mig_less, R.id.dinar_less1, R.id.dinar_less2, R.id.dinar_less3, R.id.ber_less, R.id.sopar_less1, R.id.sopar_less2, R.id.sopar_less3};
        private ImageButton esm_less, mig_less, dinar_less1, dinar_less2, dinar_less3, ber_less, sopar_less1, sopar_less2, sopar_less3;
        private ImageButton btn_less[] = {esm_less, mig_less, dinar_less1, dinar_less2, dinar_less3, ber_less, sopar_less1, sopar_less2, sopar_less3};
    private int ids_more[] = {R.id.esm_more, R.id.mig_more, R.id.dinar_more1, R.id.dinar_more2, R.id.dinar_more3, R.id.ber_more, R.id.sopar_more1, R.id.sopar_more2, R.id.sopar_more3};
        private ImageButton esm_more, mig_more, dinar_more1, dinar_more2, dinar_more3, ber_more, sopar_more1, sopar_more2, sopar_more3;
        private ImageButton btn_more[] = {esm_more, mig_more, dinar_more1, dinar_more2, dinar_more3, ber_more, sopar_more1, sopar_more2, sopar_more3};

    private ImageButton btn_list;
    private ImageButton btn_compr;

    private RWeekCalendar rCalendarFragment;
        //ATRIBUTS PER LLEGIR RECURSOS
    private String[] all_menu;
    private String[] all_recipes;
    private String[] all_ingr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        all_menu = getResources().getStringArray(R.array.all_menu);
        all_recipes = getResources().getStringArray(R.array.all_recipes);
        all_ingr = getResources().getStringArray(R.array.all_ingr);

        btn_compr = (ImageButton) findViewById(R.id.btn_compr);
        btn_compr.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {enviarALlista();}});

        try {recuperar();
        } catch (IOException e) {
            llista_ingr = new IngrList();
            omplirLlista(llista_ingr);
        }

        for (int i=0; i<TV_recipes.length; i++){
            final int j = i;
            TV_recipes[i] = (TextView) findViewById(ids_recipes[i]);
            TV_recipes[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {mostrarRecepta(trobarRecepta(TV_recipes[j].getText().toString()));}});}
        for (int i=0; i<ids_comensals.length; i++){
            TV_com[i] = (TextView) findViewById(ids_comensals[i]);}
        for (int i=0; i<checks.length; i++){
            checks[i] = (CheckBox) findViewById(ids_checkbox[i]);}
        for (int i=0; i<ids_less.length; i++){
            final int j = i;
            btn_less[i] = (ImageButton) findViewById(ids_less[i]);
            btn_less[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {restarComensals(TV_com[j]);}});}
        for (int i=0; i<ids_more.length; i++){
            final int j = i;
            btn_more[i] = (ImageButton) findViewById(ids_more[i]);
            btn_more[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {sumarComensals(TV_com[j]);}});}

        crearMenu();
        crearReceptari();
        crearLlistaIngredients();
        mostrarLlista();
        init();
        calendar();
        intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("llista_sencera", tots_ingr);
    }

    //MÈTODES
    //Llegir recursos i obtenir la informació
    private void crearMenu(){this.menu = new Menu(all_menu);}
    private void crearReceptari(){
        this.receptes = new Recepta[all_recipes.length];
        for (int i=0; i<all_recipes.length; i++){
            String r = all_recipes[i];
            String[] parts = r.split(";");
            this.receptes[i] = new Recepta(parts);
        }
    }
    private void crearLlistaIngredients(){
        tots_ingr = new IngrList();
        for (int i=0; i<all_ingr.length; i++){
            String r = this.all_ingr[i];
            String[] parts = r.split(";");
            Ingredient ingr = new Ingredient(parts[0], parts[1]);
            tots_ingr.getMapingr().put(ingr.getNom().toString(), ingr);
        }
    }
    //MÈTODE QUE PERMET CANVIAR D'ACTIVITAT
    protected void init(){
        btn_list = (ImageButton) findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                esborrarCheck();
                startActivity(intent);}});
    }
    //CALENDARI SUPERIOR
    protected void calendar(){

        rCalendarFragment = new RWeekCalendar();
        rCalendarFragment.startDate(2017, 1, 1);
        rCalendarFragment.endDate(2017, 12, 31);

        Bundle args = new Bundle();

            /*Should add this attribute if you adding  the NOW_BACKGROUND or DATE_SELECTOR_BACKGROUND Attribute*/
            args.putString(RWeekCalendar.PACKAGENAME, getApplicationContext().getPackageName());
            args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.FDF_CALENDER);
            args.putInt(RWeekCalendar.CALENDER_BACKGROUND, ContextCompat.getColor(this,R.color.GreyLight));//set background color to calender
            args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND, "bg_select");//set background to the selected dates
            args.putString(RWeekCalendar.NOW_BACKGROUND,"bg_now");//set background to nowView
            args.putInt(RWeekCalendar.CURRENT_DATE_BACKGROUND,ContextCompat.getColor(this,R.color.DietMed_prin));//set color to the currentdate
            args.putInt(RWeekCalendar.PRIMARY_BACKGROUND, ContextCompat.getColor(this,R.color.Grey));//Set color to the primary views (Month name and dates)
            args.putInt(RWeekCalendar.SECONDARY_BACKGROUND, ContextCompat.getColor(this,R.color.dark_gray));//Set color to the secondary views (now view and week names)

        rCalendarFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar, rCalendarFragment);
        t.commit();

        CalenderListener listener = new CalenderListener() {
            @Override
            public void onSelectPicker() {
                //User can use any type of pickers here the below picker is only Just a example
                DatePickerDialog.newInstance(MenuActivity.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {
                //callback when a date is selcted
                mostrarDia(menu.getMenu()[mSelectedDate.getDayOfMonth()]);
                esborrarCheck();
            }
        };

        //setting the listener
        rCalendarFragment.setCalenderListener(listener);

    }
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        //This is the call back from picker used in the sample you can use custom or any other picker
        //IMPORTANT: get the year,month and date from picker you using and call setDateWeek method
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        rCalendarFragment.setDateWeek(calendar);//Sets the selected date from Picker


    }

    //MÈTODES de MENÚ
    private void mostrarDia(Dia dia){
        for (int i=0; i<ids_recipes.length; i++){
            TextView recept_text = (TextView) findViewById(ids_recipes[i]);
            recept_text.setText(dia.getDia()[i].getRecepta());
        }
    }
    private void esborrarCheck(){
        for(int i=0; i<ids_checkbox.length; i++){
            checks[i].setChecked(false);
        }
    }

    //MÈTODES de LLISTA
    private void mostrarLlista(){
        System.out.println(tots_ingr.toString());}
    private void enviarALlista() {
        if (!trobarCheck()) {
            Toast.makeText(MenuActivity.this, "No has sel·leccionat cap recepta", Toast.LENGTH_SHORT).show();
        } else {
            omplirLlista();
            esborrarCheck();
        }
    }
    private boolean trobarCheck() {
        boolean b = false;
        for (int i = 0; i < ids_checkbox.length; i++) {
            CheckBox check = (CheckBox) findViewById(ids_checkbox[i]);
            if (check.isChecked() == true) {
                b = true;
            }
        }
        return b;
    }
    private void sumarComensals(TextView com) {
        int i = Integer.parseInt(com.getText().toString());
        if(i==9){com.setText("9");}
        else{com.setText(Integer.toString(i+1));}
    }
    private void restarComensals(TextView com) {
        int i = Integer.parseInt(com.getText().toString());
        if(i==0){com.setText("1");}
        else{com.setText(Integer.toString(i-1));}
    }
    private void omplirLlista() {
        for (int i = 0; i < ids_checkbox.length; i++) {
            CheckBox check = (CheckBox) findViewById(ids_checkbox[i]);
            if (check.isChecked() == true) {
                TextView rec = (TextView) findViewById(ids_recipes[i]);
                for (int j = 0; j < all_recipes.length; j++) {
                    if (rec.getText().equals(receptes[j].getNom())) {
                        int com = Integer.parseInt(TV_com[i].getText().toString());
                        for(Ingredient k: receptes[j].getLlista().getMapingr().values()){
                            k.setQuant(k.getQuant()*(com));
                            llista_ingr.getMapingr().put(k.getNom(), k);
                            //afegirUnitats(llista_ingr);
                        }
                    }
                }
            }
        }
        intent.putExtra("llista_propia", llista_ingr);
    }
    protected void omplirLlista(IngrList llista) {
        if(llista == null){
            llista_ingr = new IngrList();
        }
        else {
            for(Ingredient i: llista.getMapingr().values()){llista_ingr.getMapingr().put(i.getNom(),i);}
            //afegirUnitats(llista);
        }
        guardar();
    }
    //MÈTODES de RECEPTES
    private void mostrarRecepta(int i){
        if(i==-1){
            Toast.makeText(this, "Aquesta recepta no la tenim",
                    Toast.LENGTH_SHORT).show();

        } else {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.activity_recipe, null);
            TextView mRec = (TextView) mView.findViewById(R.id.rec);
            TextView mElab = (TextView) mView.findViewById(R.id.elab);
            TextView mIngr = (TextView) mView.findViewById(R.id.ingr);

            mRec.setText(this.receptes[i].getNom());
            mElab.setText(this.receptes[i].getElaboracio());
            mIngr.setText(this.receptes[i].getLlista().toString());

            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
        }

    }
    private int trobarRecepta (String s){
        int i = -1;
        for(int j=0;j<receptes.length;j++){
            if(receptes[j].getNom().equals(s)){
                i = j;
            }
        }
        return i;
    }

/*
    private String trobarUnitats(Ingredient ingr){
        if(tots_ingr.getMapingr().containsKey(ingr.getNom())){
            return tots_ingr.getMapingr().get(ingr.getNom()).getUnitats().toString();
        }
        return null;
    }
    private IngrList afegirUnitats(IngrList llista){
        for(Ingredient i: llista.getMapingr().values()) {
            llista.getMapingr().get(i).setUnitats(trobarUnitats(i));
        }
        return llista;
    }
*/
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


