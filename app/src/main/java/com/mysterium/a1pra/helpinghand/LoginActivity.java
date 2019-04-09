package com.mysterium.a1pra.helpinghand;
/*
 * Author: Prabhutva Agrawal
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

	EditText usernameEt, passwordEt, nameEt, newPasswordEt;
	Button loginButton, clearButton;
	TextView loginTv;
	String username, password, name, usernameCheck, passwordCheck;
	SharedPreferences sharedPreferences;
	LinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		linearLayout = findViewById(R.id.login_ll);
		sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
		final SharedPreferences.Editor editor = sharedPreferences.edit();
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
		usernameEt = findViewById(R.id.user_et);
		passwordEt = findViewById(R.id.pass_et);
		newPasswordEt = findViewById(R.id.newpass_et);
		nameEt = findViewById(R.id.name_et);
		loginTv = findViewById(R.id.login_tv);
		loginButton = findViewById(R.id.login_b);
		clearButton = findViewById(R.id.clear_b);
		newPasswordEt.setVisibility(View.GONE);


		username = sharedPreferences.getString("username", null);
		password = sharedPreferences.getString("password", null);
		usernameCheck = sharedPreferences.getString("checkuser", null);
		passwordCheck = sharedPreferences.getString("checkpassword", null);

		usernameEt.setText(username);
		passwordEt.setText(password);

		if (sharedPreferences.getString("checkuser", null) == null || sharedPreferences.getString("checkpass", null) == null) {
			loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					//change

					usernameCheck = usernameEt.getText().toString();
					passwordCheck = passwordEt.getText().toString();

					username = usernameCheck;
					password = passwordCheck;
					name = nameEt.getText().toString();

					Intent intent = new Intent(LoginActivity.this, HomeActivity.class);


					if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
						Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
					} else {

						intent.putExtra("name", name);
						editor.putString("name", name);
						editor.commit();
						intent.putExtra("username", username);
						intent.putExtra("password", password);
						intent.putExtra("usernamecheck", usernameCheck);
						intent.putExtra("passwordcheck", passwordCheck);
						startActivity(intent);
						finish();

					}


					editor.putString("username", username);
					editor.putString("password", password);
					editor.putString("checkuser", usernameCheck);
					editor.putString("checkpass", passwordCheck);
					editor.commit();


				}
			});
			clearButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					usernameEt.setText("");
					passwordEt.setText("");
					nameEt.setText("");
				}
			});

		} else if (sharedPreferences.getString("username", null) == null && sharedPreferences.getString("password", null) == null)//when the data is locked.
		{

			nameEt.setVisibility(View.GONE);
			loginTv.setText("Welcome, Please unlock the Locker.");
			loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					username = usernameEt.getText().toString();
					password = passwordEt.getText().toString();

					usernameCheck = sharedPreferences.getString("checkuser", null);
					passwordCheck = sharedPreferences.getString("checkpass", null);

					if (username.isEmpty() || password.isEmpty()) {
						Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
					} else {
						if (username.equals(usernameCheck) && password.equals(passwordCheck)) {
							Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

							name = sharedPreferences.getString("name", null);
							intent.putExtra("username1", username);
							intent.putExtra("password1", password);
							intent.putExtra("name", name);
							intent.putExtra("usernamecheck", usernameCheck);
							intent.putExtra("passwordcheck", passwordCheck);
							startActivity(intent);
							finish();
							editor.putString("username", username);
							editor.putString("password", password);
							editor.commit();

						} else {
							Toast.makeText(LoginActivity.this, "Privacy Invader go attack apps of other groups :P", Toast.LENGTH_SHORT).show();
						}
					}

				}
			});
			clearButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					usernameEt.setText("");
					passwordEt.setText("");
					nameEt.setText("");
				}
			});

		} else if (sharedPreferences.getString("username", null) != null && sharedPreferences.getString("password", null) == null) {
			//Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
			username = sharedPreferences.getString("username", null);
			password = sharedPreferences.getString("password", null);
			usernameCheck = sharedPreferences.getString("checkuser", null);
			passwordCheck = sharedPreferences.getString("checkpass", null);
			name = sharedPreferences.getString("name", null);

			loginTv.setText("Edit the Locker details.");
			usernameEt.setText(username);
			nameEt.setText(name);
			passwordEt.setHint("Confirm old Password");
			passwordEt.setText("");
			newPasswordEt.setVisibility(View.VISIBLE);
			loginButton.setText("Save");
			loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (passwordEt.getText().toString().equals(passwordCheck)) {

						if (newPasswordEt.getText().toString().equals(passwordCheck)) {
							Toast.makeText(LoginActivity.this, "New and old passwords cannot be same.", Toast.LENGTH_SHORT).show();
						} else {
							usernameCheck = usernameEt.getText().toString();
							passwordCheck = newPasswordEt.getText().toString();

							username = usernameCheck;
							password = passwordCheck;
							name = nameEt.getText().toString();

							Intent intent = new Intent(LoginActivity.this, HomeActivity.class);


							if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
								Toast.makeText(LoginActivity.this, "Please input valid data!!", Toast.LENGTH_SHORT).show();
							} else {

								intent.putExtra("name", name);
								editor.putString("name", name);
								editor.commit();
								intent.putExtra("username", username);
								intent.putExtra("password", password);
								intent.putExtra("usernamecheck", usernameCheck);
								intent.putExtra("passwordcheck", passwordCheck);
								startActivity(intent);
								finish();

							}


							editor.putString("username", username);
							editor.putString("password", password);
							editor.putString("checkuser", usernameCheck);
							editor.putString("checkpass", passwordCheck);
							editor.commit();
						}
					} else if (!passwordEt.getText().toString().equals(passwordCheck)) {
						Toast.makeText(LoginActivity.this, "You have forgotten your password!!", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(LoginActivity.this, "Please input complete data!!", Toast.LENGTH_SHORT).show();
					}

				}
			});
			clearButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					usernameEt.setText("");
					passwordEt.setText("");
					nameEt.setText("");
					newPasswordEt.setText("");
				}
			});
		} else {
			Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			username = sharedPreferences.getString("username", null);
			password = sharedPreferences.getString("password", null);
			usernameCheck = sharedPreferences.getString("checkuser", null);
			passwordCheck = sharedPreferences.getString("checkpass", null);
			name = sharedPreferences.getString("name", null);

			usernameEt.setText(username);
			passwordEt.setText(password);

			intent.putExtra("username1", username);
			intent.putExtra("password1", password);
			intent.putExtra("usernamecheck", usernameCheck);
			intent.putExtra("passwordcheck", passwordCheck);
			intent.putExtra("name", name);
			startActivity(intent);
			finish();
		}
		//else go to next activity
	}
}
