package edu.upc.pda.clemente.laura.menmed;


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

import java.io.Serializable;
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

    private IngrList tots_ingr;
    private IngrList llista_ingr = new IngrList();

    private Intent intent;

    //private ArrayList<IngrList> itemList = new ArrayList<>();


    private int ids_recipes[] = {R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3};
    private TextView TVEsm, TVMig, TVDin1, TVDin2, TVDin3, TVBer, TVSop1, TVSop2, TVSop3;
    private int ids_checkbox[] = {R.id.esm_check, R.id.mig_check, R.id.dinar_check1, R.id.dinar_check2, R.id.dinar_check3, R.id.ber_check, R.id.sopar_check1, R.id.sopar_check2, R.id.sopar_check3};
    private int ids_comensals[] = {R.id.esm_com, R.id.mig_com, R.id.dinar_com1, R.id.dinar_com2, R.id.dinar_com3, R.id.ber_com, R.id.sopar_com1, R.id.sopar_com1, R.id.sopar_com1};

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
        crearMenu();
        crearReceptari();
        crearLlistaIngredients();
        init();
        calendar();
        //EP!!! NO TANCA AMB EL BOTÓ "OFF", S'HA DE FER
        intent = new Intent(MenuActivity.this, ListActivity.class);
        intent.putExtra("llista_sencera", tots_ingr);
    }

    //MÈTODES
    //Llegir recursos i obtenir la informació
    private void crearMenu(){
        this.menu = new Menu(all_menu);
        //mostrarDia(menu.getMenu()[2]);
    }
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
            tots_ingr.getMapingr().put(ingr.getNom(), ingr);
        }
    }
    //MÈTODE QUE PERMET CANVIAR D'ACTIVITAT
    protected void init(){
        btn_compr = (ImageButton) findViewById(R.id.btn_compr);
            btn_compr.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {enviarALlista();}});

        TVEsm = (TextView) findViewById(R.id.esm_recept);
            TVEsm.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVEsm.getText().toString()));}});
        TVMig = (TextView) findViewById(R.id.mig_recept);
            TVMig.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVMig.getText().toString()));}});
        TVDin1 = (TextView) findViewById(R.id.dinar_recept1);
            TVDin1.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVDin1.getText().toString()));}});
        TVDin2 = (TextView) findViewById(R.id.dinar_recept2);
            TVDin2.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVDin2.getText().toString()));}});
        TVDin3 = (TextView) findViewById(R.id.dinar_recept3);
            TVDin3.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVDin3.getText().toString()));}});
        TVBer = (TextView) findViewById(R.id.ber_recept);
            TVBer.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVBer.getText().toString()));}});
        TVSop1 = (TextView) findViewById(R.id.sopar_recept1);
            TVSop1.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVSop1.getText().toString()));}});
        TVSop2 = (TextView) findViewById(R.id.sopar_recept2);
            TVSop2.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVSop2.getText().toString()));}});
        TVSop3 = (TextView) findViewById(R.id.sopar_recept3);
            TVSop3.setOnClickListener(new View.OnClickListener() {public void onClick(View view) {mostrarRecepta(trobarRecepta(TVSop3.getText().toString()));}});

        btn_list = (ImageButton) findViewById(R.id.btn_list);
            btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
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
            recept_text.setText(dia.getDia()[i]);
        }
    }

    //MÈTODES de LLISTA
    private void mostrarLlista(){
        tots_ingr.toString();
    }
    private void enviarALlista() {
        if (!trobarCheck()) {
            Toast.makeText(MenuActivity.this, "No has sel·leccionat cap recepta", Toast.LENGTH_SHORT).show();
        } else {
            omplirLlista();
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
    private void omplirLlista() {
        for (int i = 0; i < ids_checkbox.length; i++) {
            CheckBox check = (CheckBox) findViewById(ids_checkbox[i]);
            if (check.isChecked() == true) {
                TextView rec = (TextView) findViewById(ids_recipes[i]);
                for (int j = 0; j < all_recipes.length; j++) {
                    if (rec.getText().equals(receptes[j].getNom())) {
                        //System.out.println(receptes[j].toStringIngr());
                        for(Ingredient k: receptes[j].getLlista().getMapingr().values()){
                            llista_ingr.getMapingr().put(k.getNom(), k);}
                    }
                }
            }
        }
        intent.putExtra("llista_propia", llista_ingr);
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
            mIngr.setText(afegirUnitats(this.receptes[i].getLlista()).toString());

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


    private String trobarUnitats(Ingredient ingr){
        if(!tots_ingr.getMapingr().containsKey(ingr.getNom())){
            return tots_ingr.getMapingr().get(ingr.getNom()).getUnitats();
        }
        return null;
    }

    private IngrList afegirUnitats(IngrList llista){
        for(Ingredient i: llista.getMapingr().values()){
            if(!tots_ingr.getMapingr().containsKey(i.getNom())){
                llista.getMapingr().get(i).setUnitats(trobarUnitats(i));
            }
        }
        return llista;
    }



}


