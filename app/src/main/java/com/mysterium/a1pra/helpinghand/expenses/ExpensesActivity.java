package com.mysterium.a1pra.helpinghand.expenses;
/*
 * Author: Prabhutva Agrawal
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mysterium.a1pra.helpinghand.HomeActivity;
import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExpensesActivity extends AppCompatActivity {

	public static Activity activity = null;
	public static Context contextOfApplication;
	RecyclerView recyclerView;
	FloatingActionButton addB;
	FrameLayout frameLayout;
	SharedPreferences sharedPreferences;

	public static Context getContextOfApplication() {
		return contextOfApplication;
	}

	/* This is function which uses the SharedPreferences
	 * to get database from the sharedpreferences and run
	 * a loop to store data in the arraylist
	 * category is used as a key so that we dont need to
	 * create multiple functions.
	 * Created by Prabhutva Agrawal
	 */
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

	/* This is function which uses the SharedPreferences
	 * to break the arraylist into Strings and use it to
	 * update the shared preferences database.
	 * this also updates the length of the list so that
	 * new arrays can be initialized later.
	 */
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

		contextOfApplication = getApplicationContext();
		activity = this;

		sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
		final SharedPreferences.Editor editor = sharedPreferences.edit();

		ArrayList<String> itemList = new ArrayList<>();
		ArrayList<String> priceList = new ArrayList<>();
		ArrayList<String> dateList = new ArrayList<>();
		ArrayList<String> remarksList = new ArrayList<>();
		int length = sharedPreferences.getInt("Length", 0);
		itemList = getDB(itemList, length, "item");
		priceList = getDB(priceList, length, "price");
		dateList = getDB(dateList, length, "date");
		remarksList = getDB(remarksList, length, "remarks");

		String[] item = new String[length];
		String[] price = new String[length];
		String[] date = new String[length];
		String[] remarks = new String[length];

		itemList.toArray(item);
		priceList.toArray(price);
		dateList.toArray(date);
		remarksList.toArray(remarks);

		List<ExpensesModel> data = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			data.add(new ExpensesModel(item[i], price[i], date[i], remarks[i]));
		}//data is the list of objects to be set in the list item

		List<ExpensesModel> expenses = data;

		/* I shifted everything above from outside of onCreate to inside
		 * before setContentView so that I can call functions and get all
		 * data before the layout is created
		 */

		setContentView(R.layout.activity_expenses);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

		frameLayout = findViewById(R.id.expenses_ll);
		int darkTheme = sharedPreferences.getInt("darkTheme", 0);
		if (darkTheme == 1) {//change theme to dark.
			frameLayout.setBackgroundResource(R.drawable.gradientdark);
		} else if (darkTheme == 2) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("HH");
			String time = df.format(c.getTime());
			int check = Integer.parseInt(time);
			if (5 <= check && check < 11) {
				frameLayout.setBackgroundResource(R.drawable.gradientmorning);
			} else if (11 <= check && check < 16) {
				frameLayout.setBackgroundResource(R.drawable.gradientnoon);
			} else if (16 <= check && check < 19) {
				frameLayout.setBackgroundResource(R.drawable.gradientevening);
			} else {
				frameLayout.setBackgroundResource(R.drawable.gradientnight);
			}


		} else {
			//set theme bright.
			frameLayout.setBackgroundResource(R.drawable.gradient);
		}

		recyclerView = findViewById(R.id.expensesRV);
		addB = findViewById(R.id.expenses_add_b);

		ExpensesAdapter adapter = new ExpensesAdapter(expenses);
		recyclerView.setAdapter(adapter);
		recyclerView.setHasFixedSize(true);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		addB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExpensesActivity.this, com.mysterium.a1pra.helpinghand.expenses.NewExpensesActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
