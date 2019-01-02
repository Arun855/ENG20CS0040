package com.mysterium.a1pra.helpinghand;
/*
 * Author: Prabhutva Agrawal
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    ImageButton editButton;
    Button darkButton, themeButton;
    SharedPreferences sharedPreferences;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        linearLayout = findViewById(R.id.settings_ll);
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


        editButton = findViewById(R.id.edit_b);
        darkButton = findViewById(R.id.dark_b);
        themeButton = findViewById(R.id.cycle_b);
        if(darkTheme==1)
        {
            darkButton.setText("ON");
        }
        else if(darkTheme==2)
        {
            themeButton.setText("on");
        }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SettingsActivity.this, LoginActivity.class);

                editor.remove("password");

                editor.commit();
                startActivity(intent1);
                finish();

            }
        });

        darkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeButton.setText("OFF");
                int darkTheme=sharedPreferences.getInt("darkTheme",0);
                if(darkButton.getText().toString().equalsIgnoreCase("on"))
                {
                    darkButton.setText("OFF");
                    darkTheme=0;
                    editor.putInt("darkTheme",darkTheme);
                    editor.commit();
                }
                else
                {
                    darkButton.setText("ON");
                    darkTheme=1;
                    editor.putInt("darkTheme",darkTheme);
                    editor.commit();
                }
                Intent intent=new Intent(SettingsActivity.this,HomeActivity.class);
                recreate();
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkButton.setText("off");
                int darkTheme=sharedPreferences.getInt("darkTheme",0);
                if(themeButton.getText().toString().equalsIgnoreCase("on"))
                {
                    themeButton.setText("OFF");
                    darkTheme=0;
                    editor.putInt("darkTheme",darkTheme);
                    editor.commit();
                }
                else
                {
                    themeButton.setText("ON");
                    darkTheme=2;
                    editor.putInt("darkTheme",darkTheme);
                    editor.commit();
                }
                recreate();
            }
        });



    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent msg) {

        switch(keyCode) {
            case(KeyEvent.KEYCODE_BACK):
                Intent a1_intent = new Intent(this, HomeActivity.class);
                startActivity(a1_intent);
                finish();
                return true;



        }
        return false;
    }
}
