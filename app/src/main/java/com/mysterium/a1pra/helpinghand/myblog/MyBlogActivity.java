package com.mysterium.a1pra.helpinghand.myblog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.mysterium.a1pra.helpinghand.HomeActivity;
import com.mysterium.a1pra.helpinghand.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyBlogActivity extends AppCompatActivity {

	public static Context contextOfApplication;
	public static Activity activity = null;
	ImageView newEntry;
	RecyclerView recyclerView;
	FrameLayout frameLayout;
	EditText description;
	BlogAdapter adapter;
	int fav;
	FloatingActionButton post;
	SharedPreferences sharedPreferences;
	LinearLayoutManager mLayoutManager;
	private ImageView favSelectButtonNo,favSelectButtonYes;
	DatabaseReference mDatabase;

	public static Context getContextOfApplication() {
		return contextOfApplication;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_blog);
		post = findViewById(R.id.fab_post);
		description = findViewById(R.id.et_blog_description);
		favSelectButtonYes = findViewById(R.id.blog_fav_select_yes);
		favSelectButtonNo = findViewById(R.id.blog_fav_select_no);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
		frameLayout = findViewById(R.id.background);
		final SharedPreferences.Editor editor = sharedPreferences.edit();
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
		recyclerView = findViewById(R.id.rv);
		newEntry = findViewById(R.id.button_create);
		mDatabase = FirebaseDatabase.getInstance().getReference();


		contextOfApplication = getApplicationContext();
		activity = this;

		fav=sharedPreferences.getInt("favSelect",0);
		if(fav==0)
		{
			favSelectButtonNo.setVisibility(View.VISIBLE);
			favSelectButtonYes.setVisibility(View.INVISIBLE);
		}
		else
		{
			favSelectButtonYes.setVisibility(View.VISIBLE);
			favSelectButtonNo.setVisibility(View.INVISIBLE);
		}






		/*ArrayList<String> titleList = new ArrayList();
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

		List<BlogModel> blogs = data;*/

		adapter =new BlogAdapter(fav);
		recyclerView.setAdapter(adapter);
		recyclerView.setHasFixedSize(true);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		mLayoutManager.setReverseLayout(true);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);


		favSelectButtonNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				fav=1;


				favSelectButtonNo.setVisibility(View.INVISIBLE);
				favSelectButtonYes.setVisibility(View.VISIBLE);

				editor.putInt("favSelect",1);
				editor.commit();
				adapter =new BlogAdapter(fav);
				recyclerView.setAdapter(adapter);
				recyclerView.setHasFixedSize(true);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
				LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
				mLayoutManager.setReverseLayout(true);
				mLayoutManager.setStackFromEnd(true);
				recyclerView.setLayoutManager(mLayoutManager);

			}
		});
		favSelectButtonYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fav=0;


				favSelectButtonYes.setVisibility(View.INVISIBLE);
				favSelectButtonNo.setVisibility(View.VISIBLE);

				editor.putInt("favSelect",0);
				editor.commit();
				adapter =new BlogAdapter(fav);
				recyclerView.setAdapter(adapter);
				recyclerView.setHasFixedSize(true);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
				LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
				mLayoutManager.setReverseLayout(true);
				mLayoutManager.setStackFromEnd(true);
				recyclerView.setLayoutManager(mLayoutManager);
			}
		});


		newEntry.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MyBlogActivity.this, NewBlogActivity.class);
				intent.putExtra("blogdesc",description.getText().toString());
				startActivity(intent);

			}
		});

		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//POST JUST A MESSAGE
				//STORE IN FIREBASE
				//DATACHANGE LISTENER

				description.setText("");
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
