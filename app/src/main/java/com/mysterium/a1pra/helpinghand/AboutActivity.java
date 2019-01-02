package com.mysterium.a1pra.helpinghand;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AboutActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        linearLayout=findViewById(R.id.about_ll);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
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
    }
}
