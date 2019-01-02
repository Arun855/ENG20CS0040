package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 * Edited and Debugged by Prabhutva Agrawal
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysterium.a1pra.helpinghand.HomeActivity;
import com.mysterium.a1pra.helpinghand.R;

public class MyNotesActivity extends AppCompatActivity {

    FloatingActionButton newEntry;
    RecyclerView recyclerView;
    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;
    public static Context contextOfApplication;
    public static Activity activity = null;

    //Function created by Prabhutva Agrawal
    public ArrayList<String> getDB(ArrayList<String> arrayList, int length, String category) {
        sharedPreferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < length; i++) {
            String key = category + i;
            String listItem = sharedPreferences.getString(key, null);
            arrayList.add(listItem);
        }
        editor.commit();
        return arrayList;

    }
    //Function created by Prabhutva Agrawal
    public void updateDB(ArrayList<String> arrayList, String category) {
        sharedPreferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array = new String[arrayList.size()];
        arrayList.toArray(array);
        for (int i = 0; i < array.length; i++) {
            String key = category + i;
            editor.putString(key, array[i]);
            editor.commit();
        }
        editor.putInt("Length", array.length);
        editor.commit();
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        frameLayout = findViewById(R.id.background);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
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
        recyclerView = findViewById(R.id.rv);
        newEntry = findViewById(R.id.fab_create);


        contextOfApplication = getApplicationContext();
        activity = this;




        ArrayList<String> titleList = new ArrayList();
        ArrayList<String> contentList = new ArrayList();

        int length = sharedPreferences.getInt("Length", 0);

        titleList = getDB(titleList, length, "title");
        contentList = getDB(contentList, length, "content");

        String[] title = new String[length];
        String[] content = new String[length];

        titleList.toArray(title);
        contentList.toArray(content);

        List<NotesModel> data = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            data.add(new NotesModel(title[i], content[i]));
        }

        List<NotesModel> notes = data;

        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyNotesActivity.this, NewNoteActivity.class);
                startActivity(intent);
                finish();
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


