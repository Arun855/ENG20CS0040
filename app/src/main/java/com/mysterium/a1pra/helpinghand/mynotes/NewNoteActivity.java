package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 * Edited and Debugged by Prabhutva Agrawal
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class NewNoteActivity extends AppCompatActivity {
	EditText addNoteTitle, addNoteContent;
	SharedPreferences sharedPreferences;
	Button saveNote, clearNote;
	String newNoteTitle, newNoteContent;
	LinearLayout linearLayout;
	View newNoteScreen;


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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_note);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
		final SharedPreferences.Editor editor = sharedPreferences.edit();
		linearLayout = findViewById(R.id.new_note);
		int darkTheme = sharedPreferences.getInt("darkTheme", 0);
		if (darkTheme == 1) {//change theme to dark.
			linearLayout.setBackgroundResource(R.drawable.gradientdark);
		} else if (darkTheme == 2) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("HH");
			String time = df.format(c.getTime());
			int check = Integer.parseInt(time);
			if (5 <= check && check < 11) {
				linearLayout.setBackgroundResource(R.drawable.gradientmorning);
			} else if (11 <= check && check < 16) {
				linearLayout.setBackgroundResource(R.drawable.gradientnoon);
			} else if (16 <= check && check < 19) {
				linearLayout.setBackgroundResource(R.drawable.gradientevening);
			} else {
				linearLayout.setBackgroundResource(R.drawable.gradientnight);
			}


		} else {
			//set theme bright.
			linearLayout.setBackgroundResource(R.drawable.gradient);
		}

		addNoteTitle = findViewById(R.id.et_title);
		addNoteContent = findViewById(R.id.et_content);
		saveNote = findViewById(R.id.save_note_b);
		clearNote = findViewById(R.id.clear_note_b);

		newNoteScreen = findViewById(R.id.new_note);

		saveNote.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View view) {

											if (addNoteTitle.getText().toString().isEmpty() || addNoteContent.getText().toString().isEmpty()) {
												Toast.makeText(NewNoteActivity.this, "Please enter valid data.", Toast.LENGTH_SHORT).show();
											} else {
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
									}
		);
		clearNote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addNoteTitle.setText("");
				addNoteContent.setText("");
			}
		});

	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {

		switch (keyCode) {
			case (KeyEvent.KEYCODE_BACK):
				Intent a1_intent = new Intent(this, MyNotesActivity.class);
				startActivity(a1_intent);
				finish();
				return true;


		}
		return false;
	}


}



