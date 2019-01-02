package com.mysterium.a1pra.helpinghand.reminders;
/*
 * Author: Kartik Bhardwaj
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewReminderActivity extends AppCompatActivity {
    TimePicker timePicker;
    EditText et;
    Button btn1;
    SharedPreferences sharedPreferences;
    int hour1;
    int minute1;

    int hour; int min;
    int date; int month;
    int year;
    int n;
    String reminder;
    DatePicker datePicker;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        linearLayout=findViewById(R.id.new_rem_ll);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        int darkTheme=sharedPreferences.getInt("darkTheme",0);
        if(darkTheme==1)
        {//change theme to dark.
            linearLayout.setBackgroundResource(R.drawable.gradientdark);
        }
        else if(darkTheme==2){
            Calendar c=Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String time=df.format(c.getTime());
            int check=Integer.parseInt(time);
            if(5<=check&&check<11){
                linearLayout.setBackgroundResource(R.drawable.gradientmorning);
            }
            else if(11<=check&&check<16){
                linearLayout.setBackgroundResource(R.drawable.gradientnoon);
            }
            else if(16<=check&&check<19){
                linearLayout.setBackgroundResource(R.drawable.gradientevening);
            }
            else{
                linearLayout.setBackgroundResource(R.drawable.gradientnight);
            }


        }
        else{
            //set theme bright.
            linearLayout.setBackgroundResource(R.drawable.gradient);
        }

        timePicker=findViewById(R.id.tp);
        btn1=findViewById(R.id.set);
        et=findViewById(R.id.edt1);
        datePicker=findViewById(R.id.date);

        sharedPreferences=getApplicationContext().getSharedPreferences("file1",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        String no=getIntent().getStringExtra("no");
        n=Integer.parseInt(no);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(n==1)

                {
                    Calendar calendar=Calendar.getInstance();
                    hour =timePicker.getHour();
                    min=timePicker.getMinute();
                    date=datePicker.getDayOfMonth();
                    month=datePicker.getMonth();
                    year=datePicker.getYear();
                    calendar.set(Calendar.DATE,date);
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);


                    reminder=et.getText().toString();



                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,min);

                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    editor.putInt("minute1",min);
                    editor.putInt("hour1",hour);
                    editor.putInt("year1",year);
                    editor.putInt("date1",date);
                    editor.putInt("month1",month);
                    editor.putString("status1","on");
                    editor.putString("reminder1",reminder);

                    editor.commit();




                    Toast.makeText(NewReminderActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(min)+" minutes On"+Integer.toString(date)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year),Toast.LENGTH_LONG).show();

                    Intent intent_a=new Intent(NewReminderActivity.this,MyBroadcastReceiver.class);
                    intent_a.putExtra("reminder",reminder);


                    int _id=(int)System.currentTimeMillis();
                    PendingIntent pendingIntent_a=PendingIntent.getBroadcast(NewReminderActivity.this,_id,intent_a,PendingIntent.FLAG_ONE_SHOT);

                    AlarmManager alarmManager_a=(AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager_a.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent_a);
                    //Toast.makeText(MainActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(minute)+" minutes ",Toast.LENGTH_LONG).show();


                    Intent intent1=new Intent( NewReminderActivity.this,RemindersActivity.class);

                    startActivity(intent1);}
                    finish();


                if(n==2){

                    Calendar calendar=Calendar.getInstance();
                    hour =timePicker.getHour();
                    min=timePicker.getMinute();
                    reminder=et.getText().toString();
                    date=datePicker.getDayOfMonth();
                    month=datePicker.getMonth();
                    year=datePicker.getYear();
                    calendar.set(Calendar.DATE,date);
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);


                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,min);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    editor.putInt("minute2",min);
                    editor.putInt("hour2",hour);
                    editor.putInt("year2",year);
                    editor.putInt("date2",date);
                    editor.putInt("month2",month);
                    editor.putString("status2","on");
                    editor.putString("reminder2",reminder);

                    editor.commit();



                    Toast.makeText(NewReminderActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(min)+" minutes On"+Integer.toString(date)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year),Toast.LENGTH_LONG).show();

                    Intent intent_a=new Intent(NewReminderActivity.this,MyBroadcastReceiver.class);
                    intent_a.putExtra("reminder",reminder);


                    int _id=(int)System.currentTimeMillis();
                    PendingIntent pendingIntent_a=PendingIntent.getBroadcast(NewReminderActivity.this,_id,intent_a,PendingIntent.FLAG_ONE_SHOT);

                    AlarmManager alarmManager_a=(AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager_a.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent_a);
                    //Toast.makeText(MainActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(minute)+" minutes ",Toast.LENGTH_LONG).show();


                    Intent intent1=new Intent(NewReminderActivity.this,RemindersActivity.class);

                    startActivity(intent1);
                    finish();





                }

                if(n==3)
                {     reminder=et.getText().toString();
                    Calendar calendar=Calendar.getInstance();
                    hour =timePicker.getHour();
                    min=timePicker.getMinute();
                    reminder=et.getText().toString();
                    date=datePicker.getDayOfMonth();
                    month=datePicker.getMonth();
                    year=datePicker.getYear();

                    calendar.set(Calendar.DATE,date);
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);


                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,min);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    editor.putInt("minute3",min);
                    editor.putInt("hour3",hour);
                    editor.putInt("year3",year);
                    editor.putInt("date3",date);
                    editor.putInt("month3",month);
                    editor.putString("status3","on");
                    editor.putString("reminder3",reminder);

                    editor.commit();


                    Toast.makeText(NewReminderActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(min)+" minutes On"+Integer.toString(date)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year),Toast.LENGTH_LONG).show();

                    Intent intent_a=new Intent(NewReminderActivity.this,MyBroadcastReceiver.class);
                    intent_a.putExtra("reminder",reminder);


                    int _id=(int)System.currentTimeMillis();
                    PendingIntent pendingIntent_a=PendingIntent.getBroadcast(NewReminderActivity.this,_id,intent_a,PendingIntent.FLAG_ONE_SHOT);

                    AlarmManager alarmManager_a=(AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager_a.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent_a);
                    //Toast.makeText(MainActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(minute)+" minutes ",Toast.LENGTH_LONG).show();


                    Intent intent1=new Intent(NewReminderActivity.this,RemindersActivity.class);

                    startActivity(intent1);
                    finish();





                }

                if(n==4){
                    reminder=et.getText().toString();

                    Calendar calendar=Calendar.getInstance();
                    hour =timePicker.getHour();
                    min=timePicker.getMinute();
                    reminder=et.getText().toString();
                    date=datePicker.getDayOfMonth();
                    month=datePicker.getMonth();
                    year=datePicker.getYear();
                    calendar.set(Calendar.DATE,date);
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);


                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,min);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    editor.putInt("minute4",min);
                    editor.putInt("hour4",hour);
                    editor.putInt("year4",year);
                    editor.putInt("date4",date);
                    editor.putInt("month4",month);
                    editor.putString("status4","on");
                    editor.putString("reminder4",reminder);

                    editor.commit();



                    Toast.makeText(NewReminderActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(min)+" minutes On"+Integer.toString(date)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year),Toast.LENGTH_LONG).show();

                    Intent intent_a=new Intent(NewReminderActivity.this,MyBroadcastReceiver.class);
                    intent_a.putExtra("reminder",reminder);


                    int _id=(int)System.currentTimeMillis();
                    PendingIntent pendingIntent_a=PendingIntent.getBroadcast(NewReminderActivity.this,_id,intent_a,PendingIntent.FLAG_ONE_SHOT);

                    AlarmManager alarmManager_a=(AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager_a.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent_a);
                    //Toast.makeText(MainActivity.this,"reminder set at "+Integer.toString(hour)+" and "+Integer.toString(minute)+" minutes ",Toast.LENGTH_LONG).show();

                    Intent intent1=new Intent(NewReminderActivity.this,RemindersActivity.class);

                    startActivity(intent1);
                    finish();





                }

            }
        });

    }
}

