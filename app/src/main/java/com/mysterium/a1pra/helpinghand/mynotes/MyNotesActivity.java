package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import java.util.ArrayList;
import java.util.List;
import com.mysterium.a1pra.helpinghand.R;

public class MyNotesActivity extends AppCompatActivity {

    FloatingActionButton newEntry;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    CoordinatorLayout fabContainer;
    ConstraintLayout backG;
    public static Context contextOfApplication;
    public static Activity activity = null;


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
        recyclerView = findViewById(R.id.rv);
        newEntry = findViewById(R.id.fab_create);
        fabContainer = findViewById(R.id.view_fab_container);
        backG = findViewById(R.id.background);
        //final int cx=fabContainer.getRight();
        //final int cy=fabContainer.getBottom();
        //final float endRad=(float)Math.hypot(cx,cy);

        contextOfApplication = getApplicationContext();
        activity = this;

        sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


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
}


