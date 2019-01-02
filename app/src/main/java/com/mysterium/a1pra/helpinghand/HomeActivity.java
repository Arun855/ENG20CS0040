package com.mysterium.a1pra.helpinghand;
/*
 * Author: Prabhutva Agrawal
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTv;
    Button notes, reminder, expenses;
    ImageButton popupB;
    String username;
    String password;
    String usernameCheck;
    String passwordCheck;
    String name;

    SharedPreferences sharedPreferences;
    PreferenceManager preferenceManager;
    FrameLayout frameLayout;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        frameLayout=findViewById(R.id.home_ll);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        int darkTheme=sharedPreferences.getInt("darkTheme",0);
        if(darkTheme==1)
        {//change theme to dark.
            frameLayout.setBackgroundResource(R.drawable.gradientdark);
        }
        else if(darkTheme==2){
            Calendar c=Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String time=df.format(c.getTime());
            int check=Integer.parseInt(time);
            if(5<=check&&check<11){
                frameLayout.setBackgroundResource(R.drawable.gradientmorning);
            }
            else if(11<=check&&check<16){
                frameLayout.setBackgroundResource(R.drawable.gradientnoon);
            }
            else if(16<=check&&check<19){
                frameLayout.setBackgroundResource(R.drawable.gradientevening);
            }
            else{
                frameLayout.setBackgroundResource(R.drawable.gradientnight);
            }


        }
        else{
            //set theme bright.
            frameLayout.setBackgroundResource(R.drawable.gradient);
        }

        welcomeTv = findViewById(R.id.welcome_tv);
        notes = findViewById(R.id.notes_b);
        reminder = findViewById(R.id.reminder_b);
        expenses = findViewById(R.id.expenses_b);

        popupB = findViewById(R.id.popup);

        name = getIntent().getStringExtra("name");
        name= sharedPreferences.getString("name",null);
        username = getIntent().getStringExtra("username1");
        password = getIntent().getStringExtra("password1");
        usernameCheck = getIntent().getStringExtra("usernamecheck");
        passwordCheck = getIntent().getStringExtra("passwordcheck");
        welcomeTv.setText("Hi " + name + " !");



        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.mynotes.MyNotesActivity.class);
                startActivity(intent);
                finish();


            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.reminders.RemindersActivity.class);
                startActivity(intent);
                finish();

            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.expenses.ExpensesActivity.class);
                startActivity(intent);
                finish();

            }
        });




        popupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(HomeActivity.this, popupB);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.actions, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.about){
                            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                            getApplicationContext().startActivity(intent);
                            finish();

                        }
                        else if(item.getItemId()==R.id.settings){
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            getApplicationContext().startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent1 = new Intent(HomeActivity.this, LoginActivity.class);
                            username = "user";
                            password = "pass";

                            sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("username");
                            editor.remove("password");

                            editor.commit();

                            startActivity(intent1);
                            finish();
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu

            }
        });



    }



}
