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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Text;
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
    private Map<String, IngrList> mapingr = new TreeMap<String, IngrList>();
        public Map<String, IngrList> getMapingr() {return mapingr;}
        public void setMapingr (Map<String, IngrList> mapingr) {this.mapingr = mapingr;}
    private int ids_recipes[] = {R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3};

    private ImageButton btn_list;

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
        crearLlista();
        init();
        calendar();
        mostrarLlista();
        //mostrarRecepta();
        //EP!!! NO TANCA AMB EL BOTÓ "OFF", S'HA DE FER
    }

    //MÈTODES
    //Llegir recursos i obtenir la informació
    private void crearMenu(){
        this.menu = new Menu(all_menu);
        showDay(menu.getMenu()[2]);
    }
    private void crearReceptari(){
        this.receptes = new Recepta[all_recipes.length];
        for (int i=0; i<all_recipes.length; i++){
            String r = all_recipes[i];
            String[] parts = r.split(";");
            this.receptes[i] = new Recepta(parts);
        }
    }
    private void crearLlista(){
        this.mapingr = new TreeMap<String, IngrList>();
        for (int i=0; i<all_ingr.length; i++){
            String r = this.all_ingr[i];
            String[] parts = r.split(";");
            IngrList ingr = new IngrList(parts);
            mapingr.put(ingr.getNom(), ingr);
        }
    }

    //MÈTODE QUE PERMET CANVIAR D'ACTIVITAT
    protected void init(){
        btn_list = (ImageButton) findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(in);
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
                showDay(menu.getMenu()[mSelectedDate.getDayOfMonth()]);
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

    //MÈTODES
    private void showDay(Dia dia){
        for (int i=0; i<ids_recipes.length; i++){
            TextView recept_text = (TextView) findViewById(ids_recipes[i]);
            recept_text.setText(dia.getDia()[i]);
        }
    }


    private void mostrarRecepta(){
        System.out.println(this.receptes[0].toString());
    }
    private void mostrarLlista(){
        for(IngrList i: mapingr.values()){
            System.out.println("- " + i.toString());
        }
    }

    private String unitats(Ingredient ingr){
        if(!mapingr.containsKey(ingr.getNom())){
            return mapingr.get(ingr.getNom()).getUnitats();
        }
        return null;
    }
}


