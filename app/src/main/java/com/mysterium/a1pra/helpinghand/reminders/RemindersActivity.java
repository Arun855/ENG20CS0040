package com.mysterium.a1pra.helpinghand.reminders;
/*
 * Author: Kartik Bhardwaj
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysterium.a1pra.helpinghand.HomeActivity;
import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;


public class RemindersActivity extends AppCompatActivity {


	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	TextView td1;
	TextView td2;
	TextView td3;
	TextView td4;
	TextView dt1;
	TextView dt2;
	TextView dt3;
	TextView dt4;


	String reminder;


	Calendar cal = Calendar.getInstance();

	SharedPreferences sharedPreferences;
	LinearLayout linearLayout;
	int hour1;
	int minute1;
	int year;
	int month = 1;
	int date;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminders);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		linearLayout = findViewById(R.id.reminders_ll);
		sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
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

		sharedPreferences = getApplicationContext().getSharedPreferences("file1", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		btn1 = findViewById(R.id.btn1);
		btn2 = findViewById(R.id.btn2);
		btn3 = findViewById(R.id.btn3);
		btn4 = findViewById(R.id.btn4);
		tv1 = findViewById(R.id.R1);
		tv2 = findViewById(R.id.R2);
		tv3 = findViewById(R.id.R3);
		tv4 = findViewById(R.id.R4);
		td1 = findViewById(R.id.td1);
		td2 = findViewById(R.id.td2);
		td3 = findViewById(R.id.td3);
		td4 = findViewById(R.id.td4);
		dt1 = findViewById(R.id.date);
		dt2 = findViewById(R.id.date2);
		dt3 = findViewById(R.id.date3);
		dt4 = findViewById(R.id.date4);
		int minute;
		int hour;


		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RemindersActivity.this, NewReminderActivity.class);
				intent.putExtra("no", "1");
				startActivity(intent);
				finish();


			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RemindersActivity.this, NewReminderActivity.class);
				intent.putExtra("no", "2");
				startActivity(intent);
				finish();

			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RemindersActivity.this, NewReminderActivity.class);
				intent.putExtra("no", "3");
				startActivity(intent);
				finish();

			}
		});
		btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RemindersActivity.this, NewReminderActivity.class);
				intent.putExtra("no", "4");
				startActivity(intent);
				finish();
			}
		});


		if (sharedPreferences.getInt("hour1", -1) != -1 && sharedPreferences.getInt("minute1", -1) != -1) {
			hour = sharedPreferences.getInt("hour1", -1);
			minute = sharedPreferences.getInt("minute1", -1);
			month += sharedPreferences.getInt("month1", -1);
			year = sharedPreferences.getInt("year1", -1);
			date = sharedPreferences.getInt("date1", -1);
			dt1.setText(Integer.toString(date) + "/" + Integer.toString(month) + "/" + Integer.toString(year));

			td1.setText(Integer.toString(hour) + " : " + Integer.toString(minute));
			reminder = sharedPreferences.getString("reminder1", "no reminder");
			tv1.setText(reminder);
			btn1.setText("Reminder is set/Reset");


			editor.putString("status1", "off");
			editor.commit();


		}


		if (sharedPreferences.getInt("hour2", -1) != -1 && sharedPreferences.getInt("minute2", -1) != -1) {
			hour = sharedPreferences.getInt("hour2", -1);
			minute = sharedPreferences.getInt("minute2", -1);
			month += sharedPreferences.getInt("month2", -1);
			year = sharedPreferences.getInt("year2", -1);
			date = sharedPreferences.getInt("date2", -1);
			dt2.setText(Integer.toString(date) + "/" + Integer.toString(month) + "/" + Integer.toString(year));

			td2.setText(Integer.toString(hour) + " : " + Integer.toString(minute));
			reminder = sharedPreferences.getString("reminder2", "no reminder");
			tv2.setText(reminder);
			btn2.setText("Reminder is set/Reset");

			editor.putString("status2", "off");
			editor.commit();


		}

		if (sharedPreferences.getInt("hour3", -1) != -1 && sharedPreferences.getInt("minute3", -1) != -1) {
			hour = sharedPreferences.getInt("hour3", -1);
			minute = sharedPreferences.getInt("minute3", -1);
			month += sharedPreferences.getInt("month3", -1);
			year = sharedPreferences.getInt("year3", -1);
			date = sharedPreferences.getInt("date3", -1);
			dt3.setText(Integer.toString(date) + "/" + Integer.toString(month) + "/" + Integer.toString(year));

			td3.setText(Integer.toString(hour) + " : " + Integer.toString(minute));
			reminder = sharedPreferences.getString("reminder3", "no reminder");
			tv3.setText(reminder);
			btn3.setText("Reminder is set/Reset");
			editor.putString("status3", "off");
			editor.commit();

		}
		if (sharedPreferences.getInt("hour4", -1) != -1 && sharedPreferences.getInt("minute4", -1) != -1) {
			hour = sharedPreferences.getInt("hour4", -1);
			minute = sharedPreferences.getInt("minute4", -1);
			month += sharedPreferences.getInt("month4", -1);
			year = sharedPreferences.getInt("year4", -1);
			date = sharedPreferences.getInt("date4", -1);
			dt4.setText(Integer.toString(date) + "/" + Integer.toString(month) + "/" + Integer.toString(year));

			td4.setText(Integer.toString(hour) + " : " + Integer.toString(minute));
			reminder = sharedPreferences.getString("reminder4", "no reminder");
			tv4.setText(reminder);
			btn4.setText("Reminder is set/Reset");
		}


	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {

		switch (keyCode) {
			case (KeyEvent.KEYCODE_BACK):
				Intent a1_intent = new Intent(this, HomeActivity.class);
				startActivity(a1_intent);
				finish();
				return true;


		}
		return false;
	}
}
