package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 */

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.EditText;

import java.util.ArrayList;

import com.mysterium.a1pra.helpinghand.R;

public class NewNoteActivity extends AppCompatActivity {
    EditText addNoteTitle, addNoteContent;
    SharedPreferences sharedPreferences;
    FloatingActionButton saveNote;
    String newNoteTitle, newNoteContent;

    View newNoteScreen;



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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        addNoteTitle = findViewById(R.id.et_title);
        addNoteContent = findViewById(R.id.et_content);
        saveNote = findViewById(R.id.fab_save);

        newNoteScreen = findViewById(R.id.new_note);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNoteTitle = addNoteTitle.getText().toString();
                newNoteContent = addNoteContent.getText().toString();

                ArrayList<String> tempTitleList = new ArrayList();
                ArrayList<String> tempContentList = new ArrayList();

                int length = sharedPreferences.getInt("Length", 0);

                tempTitleList = getDB(tempTitleList, length, "title");
                tempContentList = getDB(tempContentList, length, "content");

                tempTitleList.add(newNoteTitle);
                tempContentList.add(newNoteContent);

                updateDB(tempTitleList, "title");
                updateDB(tempContentList, "content");

                Intent intent = new Intent(NewNoteActivity.this, MyNotesActivity.class);
                startActivity(intent);
                finish();
                }
            }
        );

    }


}



