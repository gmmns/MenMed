package edu.upc.pda.clemente.laura.menmed;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.FragmentTransaction;
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

public class MenuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //ATRIBUTS
    private String[] all_recipes;
    private int ids_recipes[] = {
            R.id.esm_recept, R.id.mig_recept, R.id.dinar_recept1, R.id.dinar_recept2, R.id.dinar_recept3, R.id.ber_recept, R.id.sopar_recept1, R.id.sopar_recept2, R.id.sopar_recept3
    };
    private Menu menu;
    private ImageButton btn_list;

    private RWeekCalendar rCalendarFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        all_recipes = getResources().getStringArray(R.array.all_recipes);
        addMenu();
        showDay(menu.getMenu()[1]);
        init();
        calendar();
    }

    private void addMenu(){
        this.menu = new Menu(all_recipes);
    }

    private void showDay(Dia dia) {
        for (int i=0; i<ids_recipes.length; i++){
            TextView recept_text = (TextView) findViewById(ids_recipes[i]);
            recept_text.setText(dia.getS_dia()[i]);
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
        args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.NORMAL_CALENDER);

       /* IMPORTANT: Customization for the calender commenting or un commenting any of the attribute below will reflect change in calender*/
        //set Calender type you want if you don't set any normal calender will be set
        /*if (getIntent().getExtras().getInt(RWeekCalendar.CALENDER_TYPE) == RWeekCalendar.FDF_CALENDER) {
            /** Set Calender type to FIRSTDAYFIRST (FDF_CALENDER)here
             * the week days will start as current day as first entry
             * eg if current day is friday calender start with fri,sat,etc
             *
            args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.FDF_CALENDER);
        } else {

            /**
             * set Calender type to normal here the week days will
             * start as normal  be like Sun,Mon etc
             *
            args.putInt(RWeekCalendar.CALENDER_TYPE, RWeekCalendar.NORMAL_CALENDER);
        }*/


//      args.putInt(RWeekCalender.CALENDER_BACKGROUND, ContextCompat.getColor(this,R.color.md_pink_700));//set background color to calender
        args.putString(RWeekCalendar.DATE_SELECTOR_BACKGROUND, "bg_select");//set background to the selected dates
//      args.putString(RWeekCalender.NOW_BACKGROUND,"bg_now");//set background to nowView
//      args.putInt(RWeekCalender.CURRENT_DATE_BACKGROUND,ContextCompat.getColor(this,R.color.md_black_1000));//set color to the currentdate
//      args.putInt(RWeekCalender.PRIMARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_white_1000));//Set color to the primary views (Month name and dates)
//      args.putInt(RWeekCalender.SECONDARY_BACKGROUND, ContextCompat.getColor(this,R.color.md_green_500));//Set color to the secondary views (now view and week names)

        rCalendarFragment.setArguments(args);

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar, rCalendarFragment);
        t.commit();

        CalenderListener listener = new CalenderListener() {
            @Override
            public void onSelectPicker() {
                //User can use any type of pickers here the below picker is only Just a example
                //DatePickerDialog.newInstance(Sample.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
            }

            @Override
            public void onSelectDate(LocalDateTime mSelectedDate) {
                //callback when a date is selcted

            }
        };

        //setting the listener
        rCalendarFragment.setCalenderListener(listener);

    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

//This is the call back from picker used in the sample you can use custom or any other picker

        //IMPORTANT: get the year,month and date from picker you using and call setDateWeek method
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, monthOfYear, dayOfMonth);
        rCalendarFragment.setDateWeek(calendar);//Sets the selected date from Picker


    }
}


