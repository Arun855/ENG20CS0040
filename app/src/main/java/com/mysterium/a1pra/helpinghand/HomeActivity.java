package com.mysterium.a1pra.helpinghand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTv;
    Button notes, reminder, expenses, lockButton;
    String username;
    String password;
    String usernameCheck;
    String passwordCheck;
    String name;

    SharedPreferences sharedPreferences;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTv=findViewById(R.id.welcome_tv);
        notes=findViewById(R.id.notes_b);
        reminder=findViewById(R.id.reminder_b);
        expenses=findViewById(R.id.expenses_b);
        lockButton=findViewById(R.id.lock_b);

        name=getIntent().getStringExtra("name");
        username=getIntent().getStringExtra("username1");
        password=getIntent().getStringExtra("password1");
        usernameCheck=getIntent().getStringExtra("usernamecheck");
        passwordCheck=getIntent().getStringExtra("passwordcheck");
        welcomeTv.setText("Hi "+name+" !");

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.mynotes.MyNotesActivity.class);
                startActivity(intent);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.reminders.RemindersActivity.class);
                startActivity(intent);
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, com.mysterium.a1pra.helpinghand.expenses.ExpensesActivity.class);
                startActivity(intent);
            }
        });

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this,LoginActivity.class);
                username="user";
                password="pass";

                sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("password");

                editor.commit();

                startActivity(intent1);
                finish();
            }
        });

    }



}
